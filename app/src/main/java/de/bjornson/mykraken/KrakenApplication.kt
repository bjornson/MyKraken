package de.bjornson.mykraken

import android.app.Application
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Description
 * Created by Björn Kechel (bkechel@gmail.com) on 09.10.2018.
 */
class KrakenApplication() : Application() {
    private val krakenService: KrakenService = initKrakenService()
    fun getKrakenService(): KrakenService {
        return this.krakenService
    }

    private fun initKrakenService(): KrakenService {

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.kraken.com/0/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create<KrakenService>(KrakenService::class.java)
    }
}

