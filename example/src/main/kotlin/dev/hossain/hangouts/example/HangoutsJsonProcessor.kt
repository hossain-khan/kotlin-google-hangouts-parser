package dev.hossain.hangouts.example

import StringListColumnAdapter
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import dev.hossain.hangouts.Database
import dev.hossain.hangouts.Parser
import dev.hossain.hangouts.model.ConversationContainer
import dev.hossain.hangouts.model.HangoutsDocument
import dev.hossain.hangouts.model.message.Participant
import hangouts.data.Conversation
import hangouts.data.ConversationQueries
import hangouts.data.ParticipantQueries
import okio.Okio
import kotlin.system.measureTimeMillis


fun main() {
    println("Begin Processing Hangouts Json ...")
    Processor.processTakeoutFile("/hangouts.json")
}

object Processor {
    private lateinit var hangoutsDocument: HangoutsDocument
    fun processTakeoutFile(path: String) {
        val inputStream = Processor::class.java.getResourceAsStream(path)

        val source = Okio.buffer(Okio.source(inputStream))

        val parseTime = measureTimeMillis { hangoutsDocument = Parser.parse(source) }
        println("Completed processing - got ${hangoutsDocument.conversations.size} conversations in ${parseTime}ms.")

        val database = buildDataBase()
        addConversations(database, hangoutsDocument.conversations)

        // TODO - Use database to get these metrics
        var attachmentCount = 0
        var participantsCount = database.participantQueries.count().executeAsOne()
        println("Total attachments used: $attachmentCount")
        println("Total participants across all conversations: $participantsCount")
    }

    fun addConversations(database: Database, conversations: List<ConversationContainer>) {
        val conversationQueries: ConversationQueries = database.conversationQueries

        conversations.forEach { container ->
            container.conversation?.conversation?.let { conversation ->
                conversationQueries.insertConversation(
                    Conversation(
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
        }


        println(conversationQueries.selectAll().executeAsList())
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
    private fun buildDataBase(): Database {
        val driver: SqlDriver = JdbcSqliteDriver("${JdbcSqliteDriver.IN_MEMORY}/tmp/hangouts-db.sqlite")
        Database.Schema.create(driver)

        val database = Database(
            driver = driver,
            conversationAdapter = Conversation.Adapter(
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