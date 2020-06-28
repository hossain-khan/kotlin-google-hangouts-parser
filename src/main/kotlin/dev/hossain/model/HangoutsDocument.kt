package dev.hossain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HangoutsDocument(
    val conversations: List<ConversationContainer>
)