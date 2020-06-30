package dev.hossain.hangouts.model

import com.squareup.moshi.JsonClass
import dev.hossain.hangouts.model.message.Id
import dev.hossain.hangouts.model.message.Participant
import dev.hossain.hangouts.model.message.ReadState
import dev.hossain.hangouts.model.message.UserChatId

/**
 * Example Data
 * ```
 * {
 *   "id": { "id": "UgxnmgLb4E" },
 *   "type": "GROUP",
 *   "self_conversation_state": {...},
 *   "read_state": [ {...}, {...} ],
 *   "has_active_hangout": false,
 *   "otr_status": "ON_THE_RECORD",
 *   "otr_toggle": "ENABLED",
 *   "current_participant": [ {...}, {...} ],
 *   "participant_data": [ {...} ],
 *   "fork_on_external_invite": false,
 *   "network_type": [ "BABEL" | "PHONE" ],
 *   "force_history_state": "NO_FORCE",
 *   "group_link_sharing_status": "LINK_SHARING_OFF"
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class Conversation(
    val id: Id,
    /**
     * - GROUP
     * - STICKY_ONE_TO_ONE
     */
    val type: String,
    val self_conversation_state: ConversationState,
    val read_state: List<ReadState> = emptyList(),
    val has_active_hangout: Boolean,
    val otr_status: String,
    val otr_toggle: String,
    val current_participant: List<UserChatId>,
    val participant_data: List<Participant>? = emptyList(),
    val fork_on_external_invite: Boolean,
    /**
     * Usually it's one or the other (even though it's a list).
     * - BABEL
     * - PHONE
     */
    val network_type: List<String>,
    val force_history_state: String,
    val group_link_sharing_status: String
)