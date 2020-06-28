package dev.hossain.model

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *   "url": "https://maps.google.com/maps?q=23.0..63,-61.6..85",
 *   "name": "Place Name",
 *   "address": {
 *     "type": [
 *       "POSTAL_ADDRESS_V2",
 *       "THING_V2",
 *       "THING"
 *     ],
 *     "postal_address_v2": {
 *       "name": "Hamburg, NY 14075, United States",
 *       "address_country": "United States",
 *       "address_locality": "Hamburg",
 *       "address_region": "New York",
 *       "postal_code": "14075"
 *     }
 *   },
 *   "geo": {
 *     "type": [
 *       "GEO_COORDINATES_V2",
 *       "THING_V2",
 *       "THING"
 *     ],
 *     "geo_coordinates_v2": {
 *       "latitude": 23.0...3,
 *       "longitude": -61.6...85
 *     }
 *   },
 *   "cluster_id": "-3415...91",
 *   "reference_id": "CnRqAAA....V0F1yAU0w80",
 *   "representative_image": {
 *     "type": [
 *       "IMAGE_OBJECT_V2",
 *       "MEDIA_OBJECT_V2",
 *       "CREATIVE_WORK_V2",
 *       "THING_V2",
 *       "THING"
 *     ],
 *     "id": "https://maps.googleapis.com/maps/api/staticmap?center=23.0....85,-61.6....91&zoom=15&markers=color:red%7C23.0...63,-61.6....85&size=400x400&sensor=false&visual_refresh=true",
 *     "image_object_v2": {
 *       "url": "https://maps.googleapis.com/maps/api/staticmap?center=23.0....85,-61.6....91&zoom=15&markers=color:red%7C23.0...63,-61.6....85&size=400x400&sensor=false&visual_refresh=true&key=AIz.....eqiA="
 *     }
 *   }
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class EmbedPlace(
    val url: String,
    val name: String? = null,
    val cluster_id: String? = null,
    val reference_id: String? = null,
    val address: AddressContainer? = null,
    val geo: GeoData,
    val representative_image: RepresentativeImage
)
