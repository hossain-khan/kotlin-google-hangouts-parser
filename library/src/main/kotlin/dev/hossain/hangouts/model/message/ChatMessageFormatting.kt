package dev.hossain.hangouts.model.message

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *   "bold": false,
 *   "italics": false,
 *   "strikethrough": false,
 *   "underline": false
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class ChatMessageFormatting(
    val bold: Boolean = false,
    val italics: Boolean = false,
    val strikethrough: Boolean = false,
    val underline: Boolean = false
)