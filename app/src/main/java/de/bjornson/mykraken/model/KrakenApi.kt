package de.bjornson.mykraken.model

import android.content.Context
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.gson.internal.LinkedTreeMap
import de.bjornson.mykraken.model.data.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Description
 * Created by Björn Kechel (bkechel@gmail.com) on 10.10.2018.
 */
class KrakenApi {
    val service: KrakenService = initKrakenService()
    lateinit var assetMap: LinkedTreeMap<String, AssetEntry>; private set

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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create<KrakenService>(KrakenService::class.java)
    }

    fun initAssets(context: Context, listener: ((Boolean) -> Unit)) {
        this.service.getAssets().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result: AssetsResult -> onAssetsReady(result.resultMap, listener) },
                        { error -> Toast.makeText(context, "Error: " + error.message, Toast.LENGTH_LONG).show() }
                )
    }

    private fun onAssetsReady(resultMap: LinkedTreeMap<String, AssetEntry>, listener: ((Boolean) -> Unit)) {
        this.assetMap = resultMap;
        listener(true)
    }

    fun hasAssets(): Boolean {
        return assetMap.size > 0
    }
}
