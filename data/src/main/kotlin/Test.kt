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

    conversationQueries.insert(id = "newid", type = "TEXT", network_type = listOf("BABEL"))
    println(conversationQueries.selectAll().executeAsList())
}