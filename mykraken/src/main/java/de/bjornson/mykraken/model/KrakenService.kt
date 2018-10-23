package de.bjornson.mykraken.model

import de.bjornson.mykraken.model.data.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Description
 * Created by Björn Kechel (bkechel@gmail.com) on 09.10.2018.
 */
@Suppress("unused")
interface KrakenService {
    /**
     * Requests ticker data for trading pairs.
     * @param pairs Comma delimited list of asset pairs to get info on. E.g. "LTCEUR,LTCUSD".
     */
    @GET("Ticker")
    fun getTicker(@Query("pair") pairs: String): Observable<TickerResult>

    @GET("OHLC")
    fun getOHLC(@Query("pair") pair: String, @Query("interval") interval: Int, @Query("since") sinceId: Int = 0): Observable<OHLCResult>

    /**
     * Requests recent spread data.
     * @param pair Asset pairs to get info on. E.g. "LTCEUR,LTCUSD".
     * @param sinceId Spread data since given id. Returns latest by default. Note: "since" is
     * inclusive so any returned data with the same time as the previous set should overwrite all
     * of the previous set's entries at that time.
     */
    @GET("Spread")
    fun getSpread(@Query("pair") pair: String, @Query("since") sinceId: Int = 0): Observable<SpreadResult>

    /**
     * Requests all tradable assets.
     */
    @GET("Assets")
    fun getAssets(): Observable<AssetsResult>

    /**
     * Requests all tradable asset pairs.
     */
    @GET("AssetPairs")
    fun getTradableAssetPairs(): Observable<AssetPairsResult>

    /**
     * Requests the servers time. This is to aid in approximating the skew time between the server and client.
     */
    @GET("Time")
    fun geServerTime(): Observable<ServerTimeResult>
}