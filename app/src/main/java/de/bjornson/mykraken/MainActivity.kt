package de.bjornson.mykraken

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import de.bjornson.mykraken.model.data.TickerEntry
import de.bjornson.mykraken.model.data.TickerLtcToEurResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainActivity : KrakenActivity() {
    private lateinit var ltcValueText: TextView
    private lateinit var refreshButton: Button
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        refresh()
    }


    private fun initViews() {
        this.ltcValueText = findViewById(R.id.main_ltc_value)
        this.refreshButton = findViewById(R.id.main_refresh_button)
        this.refreshButton.setOnClickListener { refresh() }
    }

    private fun refresh() {
        this.refreshButton.isEnabled = false
        this.disposable = this.getKrakenService().getLtcToEur().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result: TickerLtcToEurResult -> showResult(result.result.liteCoinToEur) },
                        { error -> showError(error) }
                )
    }

    private fun showError(error: Throwable) {
        Toast.makeText(applicationContext, "Error: " + error.message, Toast.LENGTH_LONG).show()
        this.refreshButton.isEnabled = true
    }

    private fun showResult(liteCoinToEur: TickerEntry) {
        val value = liteCoinToEur.bid[0].toFloat()
        val formattedValue = CurrencyFormatter.format(value, "EUR")
        this.ltcValueText.text = formattedValue
        this.refreshButton.isEnabled = true
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
