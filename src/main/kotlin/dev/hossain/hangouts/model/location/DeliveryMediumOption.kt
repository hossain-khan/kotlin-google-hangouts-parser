package dev.hossain.hangouts.model.location

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *    "delivery_medium": {
 *      "medium_type": "BABEL_MEDIUM" | "GOOGLE_VOICE_MEDIUM",
 *      "self_phone": {
 *        "e164": "+1234567890",
 *        "i18n_data": {...}
 *      }
 *     },
 *     "current_default": true,
 *     "primary": true
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class DeliveryMediumOption(
    val delivery_medium: DeliveryMedium,
    val current_default: Boolean? = null,
    val primary: Boolean? = null
)