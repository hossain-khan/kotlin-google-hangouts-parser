package dev.hossain.hangouts.model.attachment

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *  "url": "https://maps.googleapis.com/maps/api/staticmap?center=23.0....85,-61.6....91"
 *  "width": "640",
 *  "height": "640"
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class ImageObject(
    val url: String,
    val width: String? = null,
    val height: String? = null
)