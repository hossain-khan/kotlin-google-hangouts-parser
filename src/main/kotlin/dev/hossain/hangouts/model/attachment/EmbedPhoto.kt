package dev.hossain.hangouts.model.attachment

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *   "thumbnail": {
 *     "url": "https://plus.google.com/photos/albums/pun5...5296",
 *     "image_url": "https://lh5.googleusercontent.com/-NcrjlS5-FKk/s0/cap-....041.jpg",
 *     "width_px": 549,
 *     "height_px": 663
 *   },
 *   "owner_obfuscated_id": "108....5296",
 *   "album_id": "5881...7617",
 *   "photo_id": "6054....5842",
 *   "url": "https://lh5.googleusercontent.com/-NcrjlS5/VAXd57kcfWI/s0/cap-...1041.jpg",
 *   "original_content_url": "https://lh3.googleusercontent.com/mlosw.....Nx3y6Ro",
 *   "media_type": "PHOTO",
 *   "stream_id": [
 *     "BABEL_UNIQUE_ID_d4eaea91-.....a2931",
 *     "BABEL_STREAM_ID",
 *     "shared_group_605....3394"
 *   ]
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class EmbedPhoto(
    val photo_id: String,
    val album_id: String,
    val owner_obfuscated_id: String? = null,
    val url: String,
    val original_content_url: String? = null,
    val media_type: String,
    val stream_id: List<String> = emptyList(),
    val thumbnail: EmbedPhotoThumb
)