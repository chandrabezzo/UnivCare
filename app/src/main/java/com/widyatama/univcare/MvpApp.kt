package com.widyatama.univcare

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.androidnetworking.AndroidNetworking
import com.widyatama.core.util.AppLoggerUtil
import com.widyatama.core.util.LocaleUtil
import com.widyatama.univcare.di.allModule
import org.koin.android.ext.android.startKoin

/**
 * Created by bezzo on 11/01/18.
 */
class MvpApp : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleUtil.onAttach(base, LocaleUtil.getLanguage(base)))

        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        startKoin(this, allModule)

        AppLoggerUtil.init()
        AndroidNetworking.initialize(applicationContext)
    }
}