package dev.hossain.hangouts.model.event

import com.squareup.moshi.JsonClass
import dev.hossain.hangouts.model.location.DeliveryMedium
import dev.hossain.hangouts.model.message.ChatMessage
import dev.hossain.hangouts.model.message.Id
import dev.hossain.hangouts.model.message.UserChatId

/**
 * ```
 * {
 *   "conversation_id": {
 *     "id": "UgxnmgLb4E"
 *   },
 *   "sender_id": {...},
 *   "timestamp": "1369539128052528",
 *   "self_event_state": {...},
 *   "chat_message": {...}
 *   "hangout_event": {...},
 *   "event_id": "7-H0Z7-Uqf17",
 *   "advances_sort_timestamp": true,
 *   "event_otr": "ON_THE_RECORD",
 *   "delivery_medium": {
 *     "medium_type": "BABEL_MEDIUM"
 *   },
 *   "event_type": "HANGOUT_EVENT",
 *   "event_version": "1369539128052528"
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class Event(
    val conversation_id: Id,
    val sender_id: UserChatId,
    val timestamp: String,
    val self_event_state: SelfEventState,
    val chat_message: ChatMessage? = null,
    val hangout_event: HangoutEvent? = null,
    val event_id: String? = null,
    val advances_sort_timestamp: Boolean? = null,
    val event_otr: String? = null,
    val delivery_medium: DeliveryMedium? = null,
    /**
     * - HANGOUT_EVENT
     * - REGULAR_CHAT_MESSAGE
     * - REMOVE_USER
     * - RENAME_CONVERSATION
     * - ADD_USER
     * - SMS
     * - VOICEMAIL
     */
    val event_type: String? = null,
    val event_version: String? = null
)