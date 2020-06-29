import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import dev.hossain.hangouts.Database
import hangouts.data.Conversation
import hangouts.data.ConversationQueries

fun main() {
    val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    Database.Schema.create(driver)

    val database = Database(
        driver = driver,
        conversationAdapter = Conversation.Adapter(
            network_typeAdapter = StringListColumnAdapter()
        )
    )

    val conversationQueries: ConversationQueries = database.conversationQueries

    println(conversationQueries.selectAll().executeAsList())

    val timestamp = System.currentTimeMillis()
    conversationQueries.insert(
        id = "id$timestamp",
        type = "type",
        invite_timestamp = "$timestamp",
        sort_timestamp = "$timestamp",
        active_timestamp = "$timestamp",
        inviter_gaia_id = "inviter_gaia_id$timestamp",
        network_type = listOf("BABEL"),
        status = "status"
    )
    println(conversationQueries.selectAll().executeAsList())
}