package dev.hossain.model

import com.squareup.moshi.JsonClass

/**
 * ```
 * { "medium_type": "BABEL_MEDIUM" }
 * ```
 */
@JsonClass(generateAdapter = true)
data class DeliveryMedium(
    val medium_type: String
)