package dev.hossain.model.location

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *     "national_number": "(234) 567-8900",
 *     "international_number": "+1 234-567-8900",
 *     "country_code": 1,
 *     "region_code": "US",
 *     "is_valid": true,
 *     "validation_result": "IS_POSSIBLE"
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class PhoneI18n(
    val national_number: String? = null,
    val international_number: String? = null,
    val country_code: Int? = null,
    val region_code: String? = null,
    val is_valid: Boolean? = null,
    val validation_result: String? = null
)