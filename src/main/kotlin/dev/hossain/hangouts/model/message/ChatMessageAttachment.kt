package dev.hossain.hangouts.model.message

import com.squareup.moshi.JsonClass
import dev.hossain.hangouts.model.attachment.EmbedItem

/**
 * ```
 * {
 *   "id": "attachment0",
 *   "embed_item": {...}
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class ChatMessageAttachment(
    val id: String,
    val embed_item: EmbedItem? = null
)