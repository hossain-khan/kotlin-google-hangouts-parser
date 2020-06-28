package dev.hossain.hangouts.model.message

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *  "gaia_id": "11003566",
 *  "chat_id": "11003566"
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class UserChatId(
    /**
     * GAIA stands for Google Accounts and ID Administration
     *
     * The Gaia ID may be obtained with the People API, by requesting the metadata in the personFields.
     *
     * @see <a href="https://developers.google.com/people/">People API</a>
     * @see <a href="https://developers.google.com/people/api/rest/v1/people/get">People API Explorer</a>
     */
    val gaia_id: String,
    val chat_id: String
)