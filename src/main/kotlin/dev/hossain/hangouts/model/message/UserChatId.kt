package dev.hossain.hangouts.model.message

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *  "gaia_id": "11003566",
 *  "chat_id": "11003566"
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class UserChatId(
    val gaia_id: String,
    val chat_id: String
)