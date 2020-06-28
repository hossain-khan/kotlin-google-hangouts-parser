package dev.hossain.model.location

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *  "type": [
 *       "GEO_COORDINATES_V2",
 *       "THING_V2",
 *       "THING"
 *   ],
 *   "geo_coordinates_v2": {
 *       "latitude": 23.0...3,
 *       "longitude": -61.6...85
 *   }
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class GeoData(
    val geo_coordinates_v2: Coordinates,
    val type: List<String>? = emptyList()
)

