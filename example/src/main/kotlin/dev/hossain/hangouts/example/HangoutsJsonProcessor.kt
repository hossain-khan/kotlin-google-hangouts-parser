package dev.hossain.hangouts.example

import StringListColumnAdapter
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import dev.hossain.hangouts.Database
import dev.hossain.hangouts.Parser
import dev.hossain.hangouts.model.ConversationContainer
import dev.hossain.hangouts.model.HangoutsDocument
import hangouts.data.Conversation
import hangouts.data.ConversationQueries
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
        var participantsCount = 0
        val participantIds = mutableSetOf<String>()
        println("Total attachments used: $attachmentCount")
        println("Total participants across all conversations (non-unique): $participantsCount")
        println("Total participants across all conversations (unique): ${participantIds.size}")
    }

    fun addConversations(database: Database, conversations: List<ConversationContainer>) {
        val conversationQueries: ConversationQueries = database.conversationQueries

        conversations.forEach { container ->
            container.conversation?.conversation?.let { conversation ->
                conversationQueries.insert(
                    id = conversation.id.id,
                    type = conversation.type,
                    invite_timestamp = conversation.self_conversation_state.invite_timestamp,
                    sort_timestamp = conversation.self_conversation_state.sort_timestamp,
                    active_timestamp = conversation.self_conversation_state.active_timestamp,
                    inviter_gaia_id = conversation.self_conversation_state.inviter_id.gaia_id,
                    network_type = conversation.network_type,
                    status = conversation.self_conversation_state.status
                )
            }
        }


        println(conversationQueries.selectAll().executeAsList())
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
