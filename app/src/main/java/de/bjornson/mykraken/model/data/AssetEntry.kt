package de.bjornson.mykraken.model.data

import com.google.gson.annotations.SerializedName

data class AssetEntry(
        /**
         * asset class
         */
        @SerializedName("aclass")
        val aClass: String,
        /**
         * alternate name
         */
        @SerializedName("altname")
        val altName: String,
        /**
         * scaling decimal places for record keeping
         */
        @SerializedName("decimals")
        val decimals: Int,
        /**
         * scaling decimal places for output display
         */
        @SerializedName("display_decimals")
        val displayDecimals: Int
)