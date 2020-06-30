package dev.hossain.hangouts.model.location

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *    "medium_type": "BABEL_MEDIUM" | "GOOGLE_VOICE_MEDIUM" | "UNKNOWN_MEDIUM",
 *    "self_phone": {
 *        "e164": "+1234567890",
 *        "i18n_data": {...}
 *    }
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class DeliveryMedium(
    /**
     * - BABEL_MEDIUM
     * - GOOGLE_VOICE_MEDIUM
     * - UNKNOWN_MEDIUM
     */
    val medium_type: String,
    val self_phone: Phone? = null
)