package dev.hossain.model

import com.squareup.moshi.JsonClass
import dev.hossain.hangouts.model.message.Id

/**
 * ```
 * {
 *   "conversation_id": { "id": "UgxnmgLb4E" },
 *   "conversation": { ... }
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class ConversationOuter(
    val conversation_id: Id? = null,
    val conversation: Conversation
)