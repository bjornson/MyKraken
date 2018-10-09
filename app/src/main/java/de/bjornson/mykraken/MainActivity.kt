package de.bjornson.mykraken

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var ltcValueText: TextView
    private lateinit var refreshButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
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
