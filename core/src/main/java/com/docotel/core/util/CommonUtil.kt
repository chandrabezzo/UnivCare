package com.docotel.core.util

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.app.ActivityManager
import android.app.ProgressDialog
import android.content.Context
import android.content.res.AssetManager
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.icu.text.SimpleDateFormat
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.provider.Settings
import android.text.Html
import android.text.Spanned
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast

import com.docotel.core.R
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.material.floatingactionbutton.FloatingActionButton

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import java.text.NumberFormat
import java.util.Date
import java.util.Locale
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by bezzo on 26/09/17.
 */

object CommonUtil {

    private val TAG = "CommonUtil"

    @TargetApi(Build.VERSION_CODES.N)
    val timeStamp = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())

    val localeID = Locale("in", "ID")

    @SuppressLint("all")
    fun getDeviceId(context: Context) = Settings.Secure.getString(context.contentResolver,
            Settings.Secure.ANDROID_ID)

    @Throws(IOException::class)
    fun loadJSONFromAsset(context: Context, jsonFileName: String): String {

        val manager = context.assets
        val `is` = manager.open(jsonFileName)

        val size = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()

        return String(buffer, Charset.forName("UTF-8"))
    }

    fun isJSONValid(test: String?): Boolean {

        if (test == null || test.isEmpty()) {
            return false
        }

        try {
            JSONObject(test)
        } catch (ex: JSONException) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                JSONArray(test)
            } catch (ex1: JSONException) {
                return false
            }

        }

        return true
    }

    fun getPriceFormat(locale: Locale, price: Double): String {
        val currencyFormat = NumberFormat.getCurrencyInstance(locale)

        return currencyFormat.format(price)
    }

    fun getSplittedString(text: String, regex: String): Array<String> {
        return text.split(regex.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    }

    fun getTextFromHtml(html: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(html)
        }
    }

    fun setTypeface(context: Context, font: String): Typeface {
        return Typeface.createFromAsset(context.assets, font)
    }
}// this utility class is not publicy instantiable
