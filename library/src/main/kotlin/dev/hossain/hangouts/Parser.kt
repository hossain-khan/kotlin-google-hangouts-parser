package dev.hossain.hangouts

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dev.hossain.hangouts.model.HangoutsDocument
import okio.BufferedSource
import java.io.IOException

object Parser {
    @Throws(IOException::class)
    fun parse(bufferedSource: BufferedSource): HangoutsDocument {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<HangoutsDocument> = moshi.adapter<HangoutsDocument>(HangoutsDocument::class.java)

        val hangoutsDocument: HangoutsDocument = adapter.fromJson(bufferedSource)!!
        return hangoutsDocument
    }
}