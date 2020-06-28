package dev.hossain.hangouts.model.message

import com.squareup.moshi.JsonClass

/**
 * {
 *    "type": "TEXT",
 *    "text": "Sample text message sent",
 *    "formatting": {...}
 *  }
 */
@JsonClass(generateAdapter = true)
data class ChatMessageSegment(
    val type: String,
    val text: String? = null,
    val formatting: ChatMessageFormatting? = null
)