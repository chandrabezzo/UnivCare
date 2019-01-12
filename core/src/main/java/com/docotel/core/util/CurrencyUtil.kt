package com.docotel.libs.util

import android.content.res.Resources
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*


/**
 * Created by Lafran on 11/16/17.
 */

class CurrencyUtil(var currencyWord: String = "Rp") {

    internal var locale: Locale? = null
    internal var res: Resources? = null

    fun setCurrency(currency: String): CurrencyUtil {
        this.currencyWord = currencyWord
        return this
    }

    fun toRupiahFormatSimple(nominal: Float): String {
        locale = Locale("in")
        val format = NumberFormat.getInstance(locale)
        val result = format.format(nominal)

        return getCombinedResult(result)
    }

    fun toRupiahFormatSimple(nominal: Double): String {
        locale = Locale("in")
        val format = NumberFormat.getInstance(locale)
        val result = format.format(nominal)
        return getCombinedResult(result)
    }

    fun toRupiahFormatSimple(nominal: Double, isDecimal: Boolean): String {
        if (!isDecimal) {
            return toRupiahFormatSimple(nominal)
        } else {
            locale = Locale("in")
            val format = DecimalFormat("#,###,###,##0.00", DecimalFormatSymbols.getInstance(locale))
            val result = format.format(nominal)
            return getCombinedResult(result)
        }
    }

    fun toRupiahFormatSimple(nominal: Float, isDecimal: Boolean): String {
        if (!isDecimal) {
            return toRupiahFormatSimple(nominal)
        } else {
            locale = Locale("in")
            val format = DecimalFormat("#,###,###,##0.00", DecimalFormatSymbols.getInstance(locale))
            val result = format.format(nominal)
            return getCombinedResult(result)
        }
    }

    fun toRupiahFormatSimple(nominal: Int): String {
        locale = Locale("in")
        val format = NumberFormat.getInstance(locale)
        val result = format.format(nominal)

        return getCombinedResult(result)
    }

    private fun getCombinedResult(result: String): String {
        var hasil: String?= null
            if (result.contains("-"))
            {
                hasil = "- " + currencyWord + " " + result.replace("-","")
            } else
            {
                hasil = currencyWord + " " + result
            }

            return hasil
        }

    companion object {

        private val format: CurrencyUtil? = null
        val instance: CurrencyUtil
            get() = if (format == null) {
                CurrencyUtil()
            } else format

        fun clearRp(text: String): String {
            var rs = text
            rs = rs.replace("R", "")
            rs = rs.replace("p ", "")
            rs = rs.replace(" ", "")
            rs = rs.replace(".", "")
            rs = rs.replace("p", "")
            rs = rs.replace(",", "")

            return rs
        }

        fun clearPrefix(currentText: String, prefix: String): String {
            var rs = currentText
            rs = rs.replace(prefix, "")
            return rs
        }
    }
}