package dev.hossain.model

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *  "id": "UgxnmgLb4E"
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class Id(
    val id: String
)