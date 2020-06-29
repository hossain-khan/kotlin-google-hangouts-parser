import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import dev.hossain.hangouts.Database
import hangouts.data.Conversation
import hangouts.data.ConversationQueries

/**
 * Testing database classes.
 *
 * See https://cashapp.github.io/sqldelight/jvm_sqlite/
 * Also see `example` module with sample code that uses this database.
 */
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
}