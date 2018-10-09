package de.bjornson.mykraken

import de.bjornson.mykraken.model.data.TickerLtcToEurResult
import retrofit2.Call
import retrofit2.http.GET

/**
 * Description
 * Created by Björn Kechel (bkechel@gmail.com) on 09.10.2018.
 */
interface KrakenService {
    @GET("Ticker?pair=LTCEUR")
    fun getLtcToEur(): Call<TickerLtcToEurResult>
}