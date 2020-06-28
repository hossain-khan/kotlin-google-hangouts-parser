package dev.hossain.model.location

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *    "medium_type": "BABEL_MEDIUM" | "GOOGLE_VOICE_MEDIUM",
 *    "self_phone": { "e164": "+1234567890" }
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class DeliveryMedium(
    val medium_type: String,
    val self_phone: Phone? = null
)