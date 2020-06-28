package dev.hossain.hangouts.model.message

import com.squareup.moshi.JsonClass
import dev.hossain.hangouts.model.location.Phone

/**
 * ```
 * {
 *   "id": {
 *     "gaia_id": "1168...15",
 *     "chat_id": "1168...15"
 *   },
 *   "fallback_name": "+12....2",
 *   "invitation_status": "ACCEPTED_INVITATION",
 *   "participant_type": "GAIA",
 *   "new_invitation_status": "ACCEPTED_INVITATION",
 *   "in_different_customer_as_requester": false,
 *   "domain_id": "10....68"
 *   "phone_number": {
 *     "e164": "+12..02",
 *     "i18n_data": {
 *       "national_number": "28..02",
 *       "international_number": "+1 28...2",
 *       "country_code": 1,
 *       "is_valid": false | true,
 *       "validation_result": "TOO_SHORT" | "IS_POSSIBLE"
 *     }
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class Participant(
    val id: UserChatId,
    val fallback_name: String? = null,
    val invitation_status: String,
    val new_invitation_status: String? = null,
    val participant_type: String? = null,
    val domain_id: String? = null,
    val in_different_customer_as_requester: Boolean? = null,
    val phone_number: Phone? = null
)