package de.bjornson.mykraken

import java.text.NumberFormat
import java.util.*

/**
 * Description
 * Created by Björn Kechel (bkechel@gmail.com) on 09.10.2018.
 */
class CurrencyFormatter {
    companion object {
        fun format(value: Float?, currencyCode: String): String {
            val numberFormat: NumberFormat = NumberFormat.getCurrencyInstance();
            numberFormat.maximumFractionDigits = 2
            val currency: Currency = Currency.getInstance(currencyCode)
            numberFormat.currency = currency;
            return numberFormat.format(value)
        }
    }
}