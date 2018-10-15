package de.bjornson.mykraken

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.gson.internal.LinkedTreeMap
import de.bjornson.mykraken.model.data.AssetEntry
import de.bjornson.mykraken.model.data.SpreadResult
import de.bjornson.mykraken.model.data.TickerEntry
import de.bjornson.mykraken.model.data.TickerResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : KrakenActivity() {
    private lateinit var coinValueText: TextView
    private lateinit var coinLabelText: TextView
    private lateinit var refreshButton: Button
    private lateinit var assetSpinner: Spinner

    private var selectedAsset: AssetEntry? = null
    private var disposableApiRequest: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initAssets()
    }

    private fun initAssets() {
        krakenApi().initAssets(applicationContext) { success -> fillSpinner(success) }
    }

    private fun fillSpinner(success: Boolean) {
        if (success) {
            this.assetSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, krakenApi().assetMap.keys.toTypedArray())
        } else {
            Toast.makeText(applicationContext, "Error: InitAssets() failed", Toast.LENGTH_LONG).show()
        }
    }


    private fun initViews() {
        this.coinValueText = findViewById(R.id.main_coin_value)
        this.coinLabelText = findViewById(R.id.main_coin_label)
        this.assetSpinner = findViewById(R.id.main_coin_selector)
        this.refreshButton = findViewById(R.id.main_refresh_button)

        this.refreshButton.isEnabled = false;
        this.refreshButton.setOnClickListener { refresh() }

        this.assetSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                refresh()
            }
        }
    }

    private fun refresh() {
        selectedAsset = this.krakenApi().assetMap.get(assetSpinner.selectedItem) ?: return;
        this.refreshButton.isEnabled = false
        val pair: String = selectedAsset!!.altName + "EUR";
        updateLabel()
        this.disposableApiRequest = this.krakenApi().service.getTicker(pair).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result: TickerResult -> showResult(result.resultMap) },
                        { error -> showError(error) }
                )

        this.krakenApi().service.getSpread("LTCEUR", 0).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { result: SpreadResult ->
                    Log.d("Spread", result.toString())
                }
    }

    private fun updateLabel() {
        this.coinLabelText.text = selectedAsset!!.altName + " in EUR";
    }

    private fun showError(error: Throwable) {
        Toast.makeText(applicationContext, "Error: " + error.message, Toast.LENGTH_LONG).show()
        this.refreshButton.isEnabled = true
    }

    private fun showResult(resultMap: LinkedTreeMap<String, TickerEntry>) {
        resultMap.get(resultMap.keys.first())
        val value = resultMap[resultMap.keys.first()]?.bid?.get(0)?.toFloat()
        val formattedValue = CurrencyFormatter.format(value, "EUR")
        this.coinValueText.text = formattedValue
        this.refreshButton.isEnabled = true
    }

    override fun onPause() {
        super.onPause()
        disposableApiRequest?.dispose()
    }
}
