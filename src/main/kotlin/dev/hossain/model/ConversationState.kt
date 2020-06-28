package dev.hossain.model

import com.squareup.moshi.JsonClass

/**
 * ```
 * {
 *   "self_read_state": { ... },
 *   "status": "ACTIVE",
 *   "notification_level": "RING",
 *   "view": ["ARCHIVED_VIEW"],
 *   "inviter_id": { ... },
 *   "invite_timestamp": "1369539123372000",
 *   "sort_timestamp": "1369968040768557",
 *   "active_timestamp": "1369539123372000",
 *   "delivery_medium_option": [ {...}, {...} ],
 *   "is_guest": false
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class ConversationState(
    val self_read_state: ReadState,
    val status: String,
    val notification_level: String,
    val view: List<String>,
    val inviter_id: UserChatId,
    val invite_timestamp: String,
    val sort_timestamp: String,
    val active_timestamp: String? = null,
    val delivery_medium_option: List<DeliveryMediumOption>
)