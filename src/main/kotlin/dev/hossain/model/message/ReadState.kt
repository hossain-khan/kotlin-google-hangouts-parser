package dev.hossain.model

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *   "participant_id": {
 *     "gaia_id": "11003566",
 *     "chat_id": "11003566"
 *   },
 *   "latest_read_timestamp": "0" | "1369968040768557"
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class ReadState(
    val participant_id: UserChatId,
    val latest_read_timestamp: String
)