package dev.hossain.model

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *   "geo_coordinates_v2": {
 *       "latitude": 23.0...3,
 *       "longitude": -61.6...85
 *   }
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class GeoData(
    val geo_coordinates_v2: Coordinates
)

