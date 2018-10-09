package de.bjornson.mykraken.model.data

import com.google.gson.annotations.SerializedName

data class Result(
        @SerializedName("XLTCZEUR")
        val liteCoinToEur: TickerEntry
)