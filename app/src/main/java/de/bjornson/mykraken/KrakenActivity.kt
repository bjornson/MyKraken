package de.bjornson.mykraken

import androidx.appcompat.app.AppCompatActivity
import de.bjornson.mykraken.model.KrakenApi

/**
 * Description
 * Created by Björn Kechel (bkechel@gmail.com) on 09.10.2018.
 */
abstract class KrakenActivity : AppCompatActivity() {
    fun krakenApp(): KrakenApplication {
        return application as KrakenApplication
    }

    fun krakenApi(): KrakenApi {
        return krakenApp().krakenApi
    }
}