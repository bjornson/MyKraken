package de.bjornson.mykraken.model.data

import com.google.gson.annotations.SerializedName

data class TickerEntry(
        /**
         * ask array(price, whole lot volume, lot volume)
         */
        @SerializedName("a")
        val ask: List<String>,
        /**
         * bid array(price>, whole lot volume>, lot volume>)
         */
        @SerializedName("b")
        val bid: List<String>,
        /**
         * last trade closed array(price, lot volume)
         */
        @SerializedName("c")
        val lastTrade: List<String>,
        /**
         * volume array(today, last 24 hours)
         */
        @SerializedName("v")
        val volume: List<String>,
        /**
         * volume weighted average price array(today, last 24 hours)
         */
        @SerializedName("p")
        val volumeWeightedAverage: List<String>,
        /**
         * number of trades array(today, last 24 hours)
         */
        @SerializedName("t")
        val numberOfTrades: List<Int>,
        /**
         * low array(today, last 24 hours),
         */
        @SerializedName("l")
        val low: List<String>,
        /**
         * high array(today, last 24 hours)
         */
        @SerializedName("h")
        val high: List<String>,
        /**
         * today's opening price
         */
        @SerializedName("o")
        val opening: String
)