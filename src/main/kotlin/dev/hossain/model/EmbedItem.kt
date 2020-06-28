package dev.hossain.model

import com.squareup.moshi.JsonClass

/**
 * ```
 *  {
 *     "type": [
 *       "PLUS_PHOTO"
 *     ],
 *     "plus_photo": { ... },
 *     "place_v2": { ... },
 *     "thing_v2": { ... }
 *   }
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class EmbedItem(
    val type: List<String>? = emptyList(),
    val id: String? = null,
    val plus_photo: EmbedPhoto? = null,
    val place_v2: EmbedPlace? = null,
    val thing_v2: EmbedThing? = null
)