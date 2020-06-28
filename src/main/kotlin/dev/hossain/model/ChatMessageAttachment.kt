package dev.hossain.model

import com.squareup.moshi.JsonClass

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