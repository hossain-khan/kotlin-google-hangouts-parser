package dev.hossain.model

import com.squareup.moshi.JsonClass
import dev.hossain.model.message.Id
import dev.hossain.model.message.ReadState
import dev.hossain.model.message.UserChatId

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
 *   "network_type": [ "BABEL" ],
 *   "force_history_state": "NO_FORCE",
 *   "group_link_sharing_status": "LINK_SHARING_OFF"
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class Conversation(
    val id: Id,
    val type: String,
    val self_conversation_state: ConversationState,
    val read_state: List<ReadState> = emptyList(),
    val has_active_hangout: Boolean,
    val otr_status: String,
    val otr_toggle: String,
    val current_participant: List<UserChatId>,
    val fork_on_external_invite: Boolean,
    val network_type: List<String>,
    val force_history_state: String,
    val group_link_sharing_status: String
)