package de.bjornson.mykraken.model.data

import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

data class SpreadEntry(
        val time: Int,
        val bid: Float,
        val ask: Float,
        val spread: Float
)

class SpreadEntryDeserializer : JsonDeserializer<SpreadEntry> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): SpreadEntry {
        val jsonArray: JsonArray = json?.asJsonArray!!;
        val SpreadEntry = SpreadEntry(jsonArray.get(0).asInt,
                jsonArray.get(1).asFloat,
                jsonArray.get(2).asFloat,
                jsonArray.get(2).asFloat - jsonArray.get(1).asFloat
        )
        return SpreadEntry
    }
}