package dev.hossain.hangouts.example

import StringListColumnAdapter
import com.jakewharton.picnic.TextAlignment
import com.jakewharton.picnic.renderText
import com.jakewharton.picnic.table
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
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

/**
 * Main function to initiate the data parsing and database creation.
 */
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

        val database = buildDataBase(File("/tmp/hangouts-db-temp-${System.currentTimeMillis()}.sqlite"))
        val dataInsertTime = measureTimeMillis { populateDatabase(database, hangoutsDocument.conversations) }

        println("Completed inserting data in ${TimeUnit.MILLISECONDS.toSeconds(dataInsertTime)} seconds.")

        // After DB is created and populated, show stats.
        printStatistics(database)
    }

    private fun populateDatabase(database: Database, conversations: List<ConversationContainer>) {
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

    private fun printStatistics(database: Database) {
        val conversationQueries = database.conversationQueries
        val participantQueries = database.participantQueries
        val chatMessageQueries = database.chatMessageQueries

        val tableText = table {
            cellStyle {
                border = true
                alignment = TextAlignment.TopCenter
                paddingLeft = 2
                paddingRight = 2
            }

            // ------------------------------------
            // Stats for all conversation/threads
            // -----------------------------------
            row {
                cell("Conversation") {
                    rowSpan = 3
                    alignment = TextAlignment.MiddleCenter
                }
                cell("Total") { alignment = TextAlignment.MiddleRight }
                cell(conversationQueries.count().executeAsOne())
            }
            row {
                cell("Group Conversation") { alignment = TextAlignment.MiddleRight }
                cell(conversationQueries.countType("GROUP").executeAsOne())
            }
            row {
                cell("One-to-one Thread") { alignment = TextAlignment.MiddleRight }
                cell(conversationQueries.countType("STICKY_ONE_TO_ONE").executeAsOne())
            }

            // ------------------------------
            // Stats for all participants
            // ------------------------------
            row {
                cell("Participants") {
                    rowSpan = 3
                    alignment = TextAlignment.MiddleCenter
                }
                cell("Total") { alignment = TextAlignment.MiddleRight }
                cell(participantQueries.count().executeAsOne())
            }
            row {
                cell("Google Users") { alignment = TextAlignment.MiddleRight }
                cell(participantQueries.countType("GAIA").executeAsOne())
            }
            row {
                cell("Non-Google (SMS)") { alignment = TextAlignment.MiddleRight }
                cell(participantQueries.countType("OFF_NETWORK_PHONE").executeAsOne())
            }

            // ------------------------------
            // Stats for all chat messages
            // ------------------------------
            row {
                cell("Chat Message") {
                    rowSpan = 4
                    alignment = TextAlignment.MiddleCenter
                }
                cell("Total") { alignment = TextAlignment.MiddleRight }
                cell(chatMessageQueries.count().executeAsOne())
            }
            row {
                cell("Text Messages") { alignment = TextAlignment.MiddleRight }
                cell(chatMessageQueries.countMessageType("TEXT").executeAsOne())
            }
            row {
                cell("Web URL Messages") { alignment = TextAlignment.MiddleRight }
                cell(chatMessageQueries.countMessageType("LINK").executeAsOne())
            }
            row {
                cell("Longest Message Chars") { alignment = TextAlignment.MiddleRight }
                cell(chatMessageQueries.maxMessageLength().executeAsOne().max_length)
            }
        }.renderText()

        println(tableText)
    }
}


/**
 * Internal extension function that converts boolean to SQL boolean which is 0 or 1.
 */
private fun Boolean?.toSqlBoolean(): Long = if (this == null) 0L else if (this) 1L else 0L