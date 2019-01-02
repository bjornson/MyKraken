package de.bjornson.mykraken.model

import com.google.gson.GsonBuilder
import com.google.gson.internal.LinkedTreeMap
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import de.bjornson.mykraken.model.data.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Description
 * Created by Björn Kechel (bkechel@gmail.com) on 10.10.2018.
 */
@Singleton
class KrakenApi @Inject constructor() {
    val service: KrakenService = initKrakenService()
    var assetMap: LinkedTreeMap<String, AssetEntry>? = null

    private fun initKrakenService(): KrakenService {

        val customGson = GsonBuilder()
                .registerTypeAdapter(OHLCResultList::class.java, OHLCResultListDeserializer())
                .registerTypeAdapter(OHLCEntry::class.java, OHLCEntryDeserializer())
                .registerTypeAdapter(SpreadResultList::class.java, SpreadResultListDeserializer())
                .registerTypeAdapter(SpreadEntry::class.java, SpreadEntryDeserializer())
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.kraken.com/0/public/")
                .addConverterFactory(GsonConverterFactory.create(customGson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

        return retrofit.create<KrakenService>(KrakenService::class.java)
    }

    suspend fun initAssets() {
        val assetsResult = service.getAssets().await()
        assetMap = assetsResult.resultMap;
    }

    fun hasAssets(): Boolean {
        return assetMap != null
    }
}
