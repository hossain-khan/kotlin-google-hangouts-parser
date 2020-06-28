package dev.hossain.hangouts.model.location

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *     "type": [
 *       "POSTAL_ADDRESS_V2",
 *       "THING_V2",
 *       "THING"
 *     ],
 *     "postal_address_v2": {
 *       "name": "Hamburg, NY 14075, United States",
 *       "address_country": "United States",
 *       "address_locality": "Hamburg",
 *       "address_region": "New York",
 *       "postal_code": "14075"
 *     }
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class AddressContainer(
    val type: List<String> = emptyList(),
    val postal_address_v2: Address
)