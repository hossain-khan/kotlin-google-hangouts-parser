package dev.hossain.model.message

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