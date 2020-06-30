package dev.hossain.hangouts.example

import StringListColumnAdapter
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import dev.hossain.hangouts.Database
import dev.hossain.hangouts.Parser
import dev.hossain.hangouts.model.ConversationContainer
import dev.hossain.hangouts.model.HangoutsDocument
import dev.hossain.hangouts.model.event.Event
import dev.hossain.hangouts.model.message.ChatMessageSegment
import dev.hossain.hangouts.model.message.Participant
import hangouts.data.ConversationQueries
import hangouts.data.ParticipantQueries
import okio.Okio
import java.io.File
import kotlin.system.measureTimeMillis


fun main() {
    println("Begin Processing Hangouts Json ...")

    // Assumes file is in `example/src/main/resources/` directory.
    Processor.processTakeoutFile("/hangouts.json")
}

/**
 * Uses combination of [Parser] and [Database] to import parsed data into data base for data analysis.
 */
object Processor {
    /**
     * Parsed Hangouts JSON document containing conversation and related infos.
     */
    private lateinit var hangoutsDocument: HangoutsDocument

    fun processTakeoutFile(path: String) {
        val inputStream = Processor::class.java.getResourceAsStream(path)

        val source = Okio.buffer(Okio.source(inputStream))

        val parseTime = measureTimeMillis { hangoutsDocument = Parser.parse(source) }
        println("Completed processing - got ${hangoutsDocument.conversations.size} conversations in ${parseTime}ms.")

        val database = buildDataBase(File("/tmp/hangouts-db-temp.sqlite"))
        addConversations(database, hangoutsDocument.conversations)

        // TODO - Use database to get these metrics
        var attachmentCount = 0
        var participantsCount = database.participantQueries.count().executeAsOne()
        println("Total attachments used: $attachmentCount")
        println("Total participants across all conversations: $participantsCount")
    }

    fun addConversations(database: Database, conversations: List<ConversationContainer>) {
        println("Begin adding data to database. For 100MB file, it may take 2-5 minutes... hang tight!!!")
        val conversationQueries: ConversationQueries = database.conversationQueries

        conversations.forEach { container ->
            container.conversation?.conversation?.let { conversation ->
                conversationQueries.insertConversation(
                    hangouts.data.Conversation(
                        id = conversation.id.id,
                        type = conversation.type,
                        invite_timestamp = conversation.self_conversation_state.invite_timestamp,
                        sort_timestamp = conversation.self_conversation_state.sort_timestamp,
                        active_timestamp = conversation.self_conversation_state.active_timestamp,
                        inviter_gaia_id = conversation.self_conversation_state.inviter_id.gaia_id,
                        network_type = conversation.network_type,
                        status = conversation.self_conversation_state.status,
                        is_guest = conversation.self_conversation_state.is_guest.toSqlBoolean()
                    )
                )

                conversation.participant_data?.let {
                    addParticipants(database, it)
                }
            }

            // For each conversation, there is list of chat events containing text or picture message and so on
            addConversationEvents(database, container.events)
        }
    }

    private fun addConversationEvents(database: Database, conversationEvents: List<Event>) {
        val queries = database.chatEventQueries
        conversationEvents.forEach { event ->
            queries.insert(
                event_id = event.event_id,
                conversation_id = event.conversation_id.id,
                sender_gaia_id = event.sender_id.gaia_id,
                timestamp = event.timestamp,
                delivery_medium = event.delivery_medium?.medium_type,
                event_type = event.event_type,
                event_version = event.event_version
            )

            // Chat event can be of type TEXT, in that case we want to save the chat message
            val chatSegments = event.chat_message?.message_content?.segment
            if (event.event_id != null && chatSegments != null) {
                addChatMessages(database, event, chatSegments)
            }
        }
    }

    private fun addChatMessages(database: Database, event: Event, chatSegments: List<ChatMessageSegment>) {
        val queries = database.chatMessageQueries

        chatSegments.forEach { chatMessage ->
            queries.insert(
                conversation_id = event.conversation_id.id,
                chat_event_id = event.event_id!!,
                type = chatMessage.type,
                timestamp = event.timestamp,
                text = chatMessage.text,
                formatting_bold = chatMessage.formatting?.bold.toSqlBoolean(),
                formatting_italics = chatMessage.formatting?.italics.toSqlBoolean(),
                formatting_strikethrough = chatMessage.formatting?.strikethrough.toSqlBoolean(),
                formatting_underline = chatMessage.formatting?.underline.toSqlBoolean()
            )
        }
    }

    private fun addParticipants(database: Database, participants: List<Participant>) {
        val participantQueries: ParticipantQueries = database.participantQueries

        participants.forEach { participant ->
            participantQueries.insertParticipant(
                hangouts.data.Participant(
                    gaia_id = participant.id.gaia_id,
                    fallback_name = participant.fallback_name,
                    invitation_status = participant.invitation_status,
                    participant_type = participant.participant_type,
                    domain_id = participant.domain_id,
                    in_different_customer_as_requester = participant.in_different_customer_as_requester.toSqlBoolean(),
                    phone_number = participant.phone_number?.e164,
                    phone_is_valid = participant.phone_number?.i18n_data?.is_valid.toSqlBoolean(),
                    phone_validation_result = participant.phone_number?.i18n_data?.validation_result
                )
            )
        }
    }

    /**
     * Builds the Database
     * https://cashapp.github.io/sqldelight/
     */
    private fun buildDataBase(dbPath: File): Database {
        println("Creating database file at: ${dbPath.absolutePath}")
        val driver: SqlDriver = JdbcSqliteDriver("${JdbcSqliteDriver.IN_MEMORY}${dbPath.absolutePath}")
        Database.Schema.create(driver)

        val database = Database(
            driver = driver,
            conversationAdapter = hangouts.data.Conversation.Adapter(
                network_typeAdapter = StringListColumnAdapter()
            )
        )
        return database
    }
}


/**
 * Internal extension function that converts boolean to SQL boolean which is 0 or 1.
 */
private fun Boolean?.toSqlBoolean(): Long = if (this == null) 0L else if (this) 1L else 0L