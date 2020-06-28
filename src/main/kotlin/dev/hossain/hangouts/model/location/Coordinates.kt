package dev.hossain.model.location

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *    "latitude": 23.0...3,
 *    "longitude": -61.6...85
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class Coordinates(
    val latitude: Float,
    val longitude: Float
)

