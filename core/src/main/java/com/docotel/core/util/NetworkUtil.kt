package com.docotel.core.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


/**
 * Created by Docotel on 6/12/2017.
 */

object NetworkUtil {


    const val NETWORK_NOT_CONNECTED = -1
    const val NETWORK_CONNECTED = 1
    //this variable is equal with -> ConnectivityManager.EXTRA_INET_CONDITION

    fun isWifi(type: Int): Boolean {
        return type == ConnectivityManager.TYPE_WIFI
    }

    fun isBroadband(type: Int): Boolean {
        return type == ConnectivityManager.TYPE_MOBILE
    }

    fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun getConnectionManager(context: Context): ConnectivityManager? {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @SuppressLint("MissingPermission")
    fun getNetworkInfo(context: Context, connectivityManager: ConnectivityManager): NetworkInfo? {
        return if (PermissionUtil.requestAccessNetworkState(context)) {
            connectivityManager.activeNetworkInfo
        } else {
            getNetworkInfo(context, connectivityManager)
            null
        }
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

}
