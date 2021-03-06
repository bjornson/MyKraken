package de.bjornson.mykraken.model.data

import com.google.gson.*
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type

data class SpreadResultList(
        /**
         * id to be used as since when polling for new, committed Spread data
         */
        @SerializedName("last")
        var lastId: Int?,
        val entryList: MutableList<SpreadEntry> = arrayListOf()
)

class SpreadResultListDeserializer : JsonDeserializer<SpreadResultList> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): SpreadResultList {
        val jsonObject: JsonObject? = json?.asJsonObject;
        val resultList = SpreadResultList(0)
        val keySet = jsonObject?.keySet()
        keySet?.iterator()?.forEach { key ->
            if (key.equals("last")) {
                resultList.lastId = jsonObject.get(key)?.asInt
            } else {
                val entryArray: JsonArray = jsonObject.get(key).asJsonArray
                for (i in 0..(entryArray.size() - 1)) {
                    val entry = entryArray.get(i).asJsonArray
                    resultList.entryList.add(context?.deserialize<Any>(entry, SpreadEntry::class.java) as SpreadEntry)
                }
            }
        }
        return resultList
    }
}