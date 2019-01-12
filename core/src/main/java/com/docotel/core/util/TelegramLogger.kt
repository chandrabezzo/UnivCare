package com.docotel.core.util

import android.content.Context
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.docotel.core.BuildConfig
import com.docotel.core.data.network.HttpClient
import com.docotel.core.extension.AppExtensions
import org.json.JSONObject

class TelegramLogger (val context: Context, val message: String?, private val telegramId:String? = null) {

    companion object {
        val CHAT_ID = "-1001291715583"
        val CHANDRA_ID = "429263323"
    }

    //send to telegram bot `Doco Mobile Bot` API
    fun send() {
        if (BuildConfig.DEBUG) {
            message?.let {
                val appName = getApplicationName(context)
                val typeAndroid = android.os.Build.MANUFACTURER +" "+android.os.Build.MODEL
                val versionAndroid = "Android "+android.os.Build.VERSION.RELEASE
                val mention = telegramId ?: ""
                val wrappedString = "## [$appName] ##\n\n Device Name : $typeAndroid\n" +
                        " Android Version : $versionAndroid \n App Version : ${AppExtensions.getAppVersion(context)}" +
                        "\n\n >> $message \n\n$mention"
                sendMessage(wrappedString)
            }
        }
    }

    private fun sendMessage(message: String) {
        AndroidNetworking.post(BuildConfig.BASE_URL_BOT + "sendMessage?")
                .addBodyParameter("text", message)
                .addBodyParameter("chat_id", CHANDRA_ID)
                .setPriority(Priority.HIGH)
                .setOkHttpClient(HttpClient.crudClient())
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        println("Log $message has been sent to Telegram")
                    }

                    override fun onError(anError: ANError?) {
                        println(anError?.message)
                    }
                })
    }

    private fun getApplicationName(context: Context): String {
        return context.applicationInfo.loadLabel(context.packageManager).toString()
    }
}