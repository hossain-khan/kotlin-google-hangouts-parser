package dev.hossain.model.location

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *   "e164": "+1234567890",
 *   "i18n_data": {
 *     "national_number": "(234) 567-8900",
 *     "international_number": "+1 234-567-8900",
 *     "country_code": 1,
 *     "region_code": "US",
 *     "is_valid": true,
 *     "validation_result": "IS_POSSIBLE"
 *   }
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class Phone(
    /**
     * - https://en.wikipedia.org/wiki/E.164
     * - https://www.twilio.com/docs/glossary/what-e164
     */
    val e164: String,
    val i18n_data: PhoneI18n? = null
)