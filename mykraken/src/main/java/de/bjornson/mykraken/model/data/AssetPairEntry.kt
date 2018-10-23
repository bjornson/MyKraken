package de.bjornson.mykraken.model.data

import com.google.gson.annotations.SerializedName

data class AssetPairEntry(
        @SerializedName("altname")
        val altname: String,
        @SerializedName("aclass_base")
        val aclassBase: String,
        @SerializedName("base")
        val base: String,
        @SerializedName("aclass_quote")
        val aclassQuote: String,
        @SerializedName("quote")
        val quote: String,
        @SerializedName("lot")
        val lot: String,
        @SerializedName("pair_decimals")
        val pairDecimals: Int,
        @SerializedName("lot_decimals")
        val lotDecimals: Int,
        @SerializedName("lot_multiplier")
        val lotMultiplier: Int,
        @SerializedName("leverage_buy")
        val leverageBuy: List<Any>,
        @SerializedName("leverage_sell")
        val leverageSell: List<Any>,
        @SerializedName("fees")
        val fees: List<List<Int>>,
        @SerializedName("fees_maker")
        val feesMaker: List<List<Int>>,
        @SerializedName("fee_volume_currency")
        val feeVolumeCurrency: String,
        @SerializedName("margin_call")
        val marginCall: Int,
        @SerializedName("margin_stop")
        val marginStop: Int
)