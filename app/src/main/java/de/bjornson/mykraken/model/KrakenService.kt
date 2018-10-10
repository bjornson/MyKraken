package de.bjornson.mykraken.model

import de.bjornson.mykraken.model.data.AssetsResult
import de.bjornson.mykraken.model.data.TickerResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Description
 * Created by Björn Kechel (bkechel@gmail.com) on 09.10.2018.
 */
interface KrakenService {
    @GET("Ticker")
    fun getTicker(@Query("pair") pair: String): Observable<TickerResult>

    @GET("Assets")
    fun getAssets(): Observable<AssetsResult>
}