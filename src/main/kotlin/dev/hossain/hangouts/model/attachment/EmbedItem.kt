package dev.hossain.model.attachment

import com.squareup.moshi.JsonClass

/**
 * ```
 *  {
 *     "type": [
 *       "PLUS_PHOTO",
 *       "PLUS_AUDIO_V2",
 *       "PLACE_V2",
 *       "THING_V2",
 *       "THING"
 *     ],
 *     "plus_photo": { ... },
 *     "place_v2": { ... },
 *     "thing_v2": { ... },
 *     "plus_audio_v2": { ... }
 *   }
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class EmbedItem(
    val type: List<String>? = emptyList(),
    val id: String? = null,
    val plus_photo: EmbedPhoto? = null,
    val plus_audio_v2: EmbedAudio? = null,
    val place_v2: EmbedPlace? = null,
    val thing_v2: EmbedThing? = null
)