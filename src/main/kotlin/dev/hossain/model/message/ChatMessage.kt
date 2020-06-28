package dev.hossain.model

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *   "message_content": {
 *     "segment": [ {...} ],
 *     "attachment": [ {...} ]
 *   }
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class ChatMessage(
    val message_content: ChatMessageContent
)