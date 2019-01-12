package com.docotel.coreandroid

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.androidnetworking.AndroidNetworking
import com.docotel.core.util.AppLoggerUtil
import com.docotel.core.util.LocaleUtil
import com.docotel.coreandroid.di.allModule
import com.orhanobut.hawk.Hawk
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
        Hawk.init(this).build()
        AndroidNetworking.initialize(applicationContext)
    }
}