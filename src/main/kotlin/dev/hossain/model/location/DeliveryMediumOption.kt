package dev.hossain.model.location

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *    "delivery_medium": {
 *      "medium_type": "BABEL_MEDIUM"
 *     },
 *     "current_default": true
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class DeliveryMediumOption(
    val delivery_medium: DeliveryMedium,
    val current_default: Boolean
)