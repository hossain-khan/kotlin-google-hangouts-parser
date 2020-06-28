package dev.hossain.hangouts.model

import com.squareup.moshi.JsonClass
import dev.hossain.hangouts.model.event.Event

/**
 * This is the root JSON document for the "Hangouts.json" file received from Google Takeout.
 *
 * Here is redacted snapshot of the document:
 *
 * ```
 * {
 *   "conversations": [
 *       {
 *         "conversation": {
 *           "conversation_id": {},
 *           "conversation": {...},
 *         }
 *        "events" : [ {...}, {...} ]
 *     },
 *     { ... },
 *     { ... }
 *   ]
 * }
 * ```
 * @see Conversation
 * @see Event
 */
@JsonClass(generateAdapter = true)
data class HangoutsDocument(
    val conversations: List<ConversationContainer>
)