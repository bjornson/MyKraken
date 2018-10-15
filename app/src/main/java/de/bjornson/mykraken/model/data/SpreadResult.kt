package de.bjornson.mykraken.model.data

import com.google.gson.annotations.SerializedName

data class SpreadResult(
        val error: List<Any>,
        @SerializedName("result")
        val result: SpreadResultList
)