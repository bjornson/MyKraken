package de.bjornson.mykraken

import android.app.Application
import de.bjornson.mykraken.model.KrakenApi

/**
 * Description
 * Created by Björn Kechel (bkechel@gmail.com) on 09.10.2018.
 */
class KrakenApplication() : Application() {
    val krakenApi: KrakenApi = KrakenApi()
}

