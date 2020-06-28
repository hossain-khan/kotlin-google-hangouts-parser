package dev.hossain.hangouts.example

import dev.hossain.hangouts.Parser
import dev.hossain.hangouts.model.HangoutsDocument
import okio.Okio
import kotlin.system.measureTimeMillis


fun main() {
    println("Begin Processing Hangouts Json ...")
    Processor.processTakeoutFile("/hangouts.json")
}

object Processor {
    private lateinit var hangoutsDocument: HangoutsDocument
    fun processTakeoutFile(path: String) {
        val inputStream = Processor::class.java.getResourceAsStream(path)

        val source = Okio.buffer(Okio.source(inputStream))

        val parseTime = measureTimeMillis { hangoutsDocument = Parser.parse(source) }
        println("Completed processing - got ${hangoutsDocument.conversations.size} conversations in ${parseTime}ms.")

        var attachmentCount = 0
        var participantsCount = 0
        val participantIds = mutableSetOf<String>()
        hangoutsDocument.conversations.forEach { conversationContainer ->
            conversationContainer.conversation?.conversation?.participant_data?.let {
                participantsCount += it.size
                participantIds.addAll(it.map { it.id.gaia_id })
            }
            conversationContainer.events.forEach { event ->
                event.chat_message?.message_content?.attachment?.forEach { attachment ->
                    if (attachment.embed_item != null) {
                        // Checks the attachments
                        attachmentCount++
                    }
                }
            }
        }
        println("Total attachments used: $attachmentCount")
        println("Total participants across all conversations (non-unique): $participantsCount")
        println("Total participants across all conversations (unique): ${participantIds.size}")
    }
}
