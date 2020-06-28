package dev.hossain.hangouts

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
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

        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<HangoutsDocument> = moshi.adapter<HangoutsDocument>(HangoutsDocument::class.java)

        val hangoutsDocument: HangoutsDocument = adapter.fromJson(source)!!
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
