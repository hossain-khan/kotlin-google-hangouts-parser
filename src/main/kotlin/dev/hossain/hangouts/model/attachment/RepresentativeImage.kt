package dev.hossain.hangouts.model.attachment

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *    "type": [
 *      "IMAGE_OBJECT_V2",
 *      "MEDIA_OBJECT_V2",
 *      "CREATIVE_WORK_V2",
 *      "THING_V2",
 *      "THING"
 *    ],
 *    "id": "https://maps.googleapis.com/maps/api/staticmap?center=23.0....85,-61.6....91&zoom=15&markers=color:red%7C23.0...63,-61.6....85&size=400x400&sensor=false&visual_refresh=true",
 *    "image_object_v2": {
 *      "url": "https://maps.googleapis.com/maps/api/staticmap?center=23.0....85,-61.6....91&zoom=15&markers=color:red%7C23.0...63,-61.6....85&size=400x400&sensor=false&visual_refresh=true&key=AIz.....eqiA="
 *      "width": "640",
 *      "height": "640"
 *    }
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class RepresentativeImage(
    val id: String? = null,
    val type: List<String> = emptyList(),
    val image_object_v2: ImageObject
)

