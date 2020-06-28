package dev.hossain.hangouts.model.message

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *  "gaia_id": "110...66",
 *  "chat_id": "110...566"
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
     * > TIP: How to find your own GAIA?
     * > Go to https://get.google.com/albumarchive and the 21 numbers that reveal itself in address bar.
     *
     * @see <a href="https://developers.google.com/people/">People API</a>
     * @see <a href="https://developers.google.com/people/api/rest/v1/people/get">People API Explorer</a>
     */
    val gaia_id: String,
    val chat_id: String
)