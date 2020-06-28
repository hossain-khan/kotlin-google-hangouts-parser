package dev.hossain.model.location

import com.squareup.moshi.JsonClass

/**
 * ```
 * { "e164": "+1234567890" }
 * ```
 */
@JsonClass(generateAdapter = true)
data class Phone(
    /**
     * - https://en.wikipedia.org/wiki/E.164
     * - https://www.twilio.com/docs/glossary/what-e164
     */
    val e164: String
)