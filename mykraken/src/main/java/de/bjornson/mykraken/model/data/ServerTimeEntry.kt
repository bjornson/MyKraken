package de.bjornson.mykraken.model.data

import com.google.gson.annotations.SerializedName

data class ServerTimeEntry(
        /**
         * ask array(price, whole lot volume, lot volume)
         */
        @SerializedName("unixtime")
        val unixTime: String,
        /**
         * ask array(price, whole lot volume, lot volume)
         */
        @SerializedName("rfc1123")
        val rfc1123: String
)