package dev.hossain.model

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *     "url": "https://plus.google.com/photos/albums/pun5...5296",
 *     "image_url": "https://lh5.googleusercontent.com/-NcrjlS5-FKk/s0/cap-....041.jpg",
 *     "width_px": 549,
 *     "height_px": 663
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class EmbedPhotoThumb(
    val url: String? = null,
    val image_url: String,
    val width_px: Int,
    val height_px: Int
)