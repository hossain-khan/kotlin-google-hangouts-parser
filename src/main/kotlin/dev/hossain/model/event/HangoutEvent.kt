package dev.hossain.model

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *  "event_type": "START_HANGOUT",
 *  "participant_id": [
 *    {
 *       "gaia_id": "107431117",
 *       "chat_id": "107431117"
 *    }
 *  ]
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class HangoutEvent(
    val event_type: String,
    val participant_id: List<UserChatId> = emptyList()
)