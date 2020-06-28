package dev.hossain.model

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *   "url": "https://lh3.googleusercontent.com/-sqBj..sI/s0/2015..04.jpg",
 *   "owner_obfuscated_id": "1088...550",
 *   "album_id": "611....329",
 *   "photo_id": "611....2610",
 *   "embed_url": "https://plus.google.com/photos/albums/pm1dvi...1uv6u?pid=6112...550",
 *   "duration": "43000",
 *   "media_key": "AF1Q....LOTT"
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class EmbedAudio(
    val url: String,
    val owner_obfuscated_id: String? = null,
    val album_id: String,
    val photo_id: String,
    val embed_url: String? = null,
    val duration: String,
    val media_key: String
)