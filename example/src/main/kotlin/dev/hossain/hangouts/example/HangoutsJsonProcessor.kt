package dev.hossain.hangouts.example

import dev.hossain.hangouts.Parser
import dev.hossain.hangouts.model.HangoutsDocument
import okio.Okio


fun main() {
    println("Begin Processing Hangouts Json ...")
    Processor.processTakeoutFile("/hangouts.json")
}

object Processor {
    fun processTakeoutFile(path: String) {
        val inputStream = Processor::class.java.getResourceAsStream(path)

        val source = Okio.buffer(Okio.source(inputStream))

        val hangoutsDocument: HangoutsDocument = Parser.parse(source)
        println("Completed processing - got ${hangoutsDocument.conversations.size} conversations.")
        hangoutsDocument.conversations.stream().forEach {
            it.events.stream().forEach { event ->
                event.chat_message?.message_content?.attachment?.stream()?.forEach { attachment ->
                    if (attachment.embed_item != null) {
                        // Checks the attachments
                        println(attachment.embed_item)
                    }
                }
            }
        }
    }
}
