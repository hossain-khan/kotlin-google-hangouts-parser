import com.squareup.sqldelight.ColumnAdapter

/**
 * https://cashapp.github.io/sqldelight/jvm_sqlite/types/#custom-column-types
 */
class StringListColumnAdapter : ColumnAdapter<List<String>, String> {
    override fun decode(databaseValue: String) = databaseValue.split(",")
    override fun encode(value: List<String>) = value.joinToString(separator = ",")
}