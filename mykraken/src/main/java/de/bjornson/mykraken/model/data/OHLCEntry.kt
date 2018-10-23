package de.bjornson.mykraken.model.data

import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

data class OHLCEntry(
        val time: Int,
        val open: Float,
        val high: Float,
        val low: Float,
        val close: Float,
        val vwap: Float,
        val volume: Float,
        val count: Int
)

class OHLCEntryDeserializer : JsonDeserializer<OHLCEntry> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OHLCEntry {
        val jsonArray: JsonArray = json?.asJsonArray!!;
        val ohlcEntry = OHLCEntry(jsonArray.get(0).asInt,
                jsonArray.get(1).asFloat,
                jsonArray.get(2).asFloat,
                jsonArray.get(3).asFloat,
                jsonArray.get(4).asFloat,
                jsonArray.get(5).asFloat,
                jsonArray.get(6).asFloat,
                jsonArray.get(7).asInt
        )
        return ohlcEntry
    }
}