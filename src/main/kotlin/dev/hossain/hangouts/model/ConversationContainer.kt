package dev.hossain.model

import com.squareup.moshi.JsonClass
import dev.hossain.hangouts.model.event.Event

/**
 * ```
 * {
 *    "conversation": {
 *      "conversation_id": {},
 *      "conversation": {...},
 *   }
 *   "events" : [ {...}, {...} ]
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class ConversationContainer(
    val conversation: ConversationOuter? = null,
    val events: List<Event> = emptyList()
)