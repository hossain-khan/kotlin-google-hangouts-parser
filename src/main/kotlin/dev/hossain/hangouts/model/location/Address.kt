package dev.hossain.model.location

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *   "name": "Hamburg, NY 14075, United States",
 *   "address_country": "United States",
 *   "address_locality": "Hamburg",
 *   "address_region": "New York",
 *   "postal_code": "14075"
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class Address(
    val name: String? = null,
    val address_country: String? = null,
    val address_locality: String? = null,
    val address_region: String? = null,
    val postal_code: String? = null
)