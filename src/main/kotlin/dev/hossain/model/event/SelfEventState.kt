package dev.hossain.model.event

import com.squareup.moshi.JsonClass
import dev.hossain.model.message.UserChatId

/***
 * ```
 * {
 *  "user_id": {
 *      "gaia_id": "107431117",
 *      "chat_id": "107431117"
 *  },
 *  "notification_level": "RING"
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class SelfEventState(
    val user_id: UserChatId,
    val notification_level: String,
    val client_generated_id: String? = null
)