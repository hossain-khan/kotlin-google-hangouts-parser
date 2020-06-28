package dev.hossain.model

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *  "url": "https://maps.googleapis.com/maps/api/staticmap?center=23.0....85,-61.6....91"
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class Url(
    val url: String
)