package dev.hossain.model

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *   "segment": [
 *       {
 *         "type": "TEXT",
 *         "text": "Jaige asos",
 *         "formatting": {}
 *       }
 *   ],
 *   "attachment": [
 *      "id": "attachment0",
 *      "embed_item": { ... }
 *   ]
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class ChatMessageContent(
    val segment: List<ChatMessageSegment>? = emptyList(),
    val attachment: List<ChatMessageAttachment>? = emptyList()
)