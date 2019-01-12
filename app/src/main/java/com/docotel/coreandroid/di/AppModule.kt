
package com.docotel.coreandroid.di

import com.docotel.core.data.session.SessionHelper
import com.docotel.core.util.SchedulerProviderUtil
import com.docotel.coreandroid.adapter.recyclerView.KaryawanRVAdapter
import com.docotel.coreandroid.adapter.spinner.JabatanSPAdapter
import com.docotel.coreandroid.data.local.LocalStorageHelper
import com.docotel.coreandroid.data.network.ApiHelper
import com.docotel.coreandroid.features.auth.AuthContracts
import com.docotel.coreandroid.features.auth.AuthPresenter
import com.docotel.coreandroid.features.auth.forgotPassword.ForgotPasswordContracts
import com.docotel.coreandroid.features.auth.forgotPassword.ForgotPasswordPresenter
import com.docotel.coreandroid.features.auth.login.LoginContracts
import com.docotel.coreandroid.features.auth.login.LoginPresenter
import com.docotel.coreandroid.features.auth.register.RegisterContracts
import com.docotel.coreandroid.features.auth.register.RegisterPresenter
import com.docotel.coreandroid.features.main.MainContracts
import com.docotel.coreandroid.features.main.MainPresenter
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val appModule = module {
    single { LocalStorageHelper(androidApplication()) }
    single { SessionHelper() }
    single { CompositeDisposable() }
    single { Gson() }
    single { SchedulerProviderUtil() }
    single { ApiHelper(get()) }
}

val presenterModule = module {
    factory { MainPresenter<MainContracts.View>(get(), get(), get(), get(), get()) }
    factory { AuthPresenter<AuthContracts.View>(get(), get(), get(), get(), get()) }
    factory { LoginPresenter<LoginContracts.View>(get(), get(), get(), get(), get()) }
    factory { RegisterPresenter<RegisterContracts.View>(get(), get(), get(), get(), get()) }
    factory { ForgotPasswordPresenter<ForgotPasswordContracts.View>(get(), get(), get(), get(), get()) }
}

val rvAdapterModule = module {
    factory { KaryawanRVAdapter(get(), ArrayList()) }
}

val spAdapterModule = module {
    factory { JabatanSPAdapter(get(), ArrayList()) }
}

val allModule = listOf(appModule, presenterModule, rvAdapterModule, spAdapterModule)


