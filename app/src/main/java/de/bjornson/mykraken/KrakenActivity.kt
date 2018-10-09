package de.bjornson.mykraken

import androidx.appcompat.app.AppCompatActivity

/**
 * Description
 * Created by Björn Kechel (bkechel@gmail.com) on 09.10.2018.
 */
abstract class KrakenActivity : AppCompatActivity() {
    fun getKrakenApp(): KrakenApplication {
        return application as KrakenApplication
    }

    fun getKrakenService(): KrakenService {
        return getKrakenApp().getKrakenService()
    }
}