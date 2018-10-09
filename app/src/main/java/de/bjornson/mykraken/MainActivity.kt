package de.bjornson.mykraken

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var ltcValueText: TextView
    private lateinit var refreshButton: Button
    private lateinit var krakenService: KrakenService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRetrofit()
        initViews()
    }

    private fun initRetrofit() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.kraken.com/0/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        this.krakenService = retrofit.create<KrakenService>(KrakenService::class.java)
    }


    private fun initViews() {
        this.ltcValueText = findViewById(R.id.main_ltc_value)
        this.refreshButton = findViewById(R.id.main_refresh_button)
        this.refreshButton.setOnClickListener { refresh() }
    }

    private fun refresh() {
        this.refreshButton.isEnabled = false
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
