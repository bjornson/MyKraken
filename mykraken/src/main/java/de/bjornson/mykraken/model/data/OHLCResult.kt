package de.bjornson.mykraken.model.data

import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap

data class OHLCResult(
        val error: List<Any>,
        @SerializedName("result")
        val result: OHLCResultList
)