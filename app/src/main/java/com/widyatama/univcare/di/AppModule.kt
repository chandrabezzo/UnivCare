
package com.widyatama.coreandroid.di

import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.coreandroid.adapter.recyclerView.KaryawanRVAdapter
import com.widyatama.coreandroid.adapter.spinner.JabatanSPAdapter
import com.widyatama.coreandroid.data.local.LocalStorageHelper
import com.widyatama.coreandroid.data.network.ApiHelper
import com.widyatama.coreandroid.features.auth.AuthContracts
import com.widyatama.coreandroid.features.auth.AuthPresenter
import com.widyatama.coreandroid.features.auth.forgotPassword.ForgotPasswordContracts
import com.widyatama.coreandroid.features.auth.forgotPassword.ForgotPasswordPresenter
import com.widyatama.coreandroid.features.auth.login.LoginContracts
import com.widyatama.coreandroid.features.auth.login.LoginPresenter
import com.widyatama.coreandroid.features.auth.register.RegisterContracts
import com.widyatama.coreandroid.features.auth.register.RegisterPresenter
import com.widyatama.coreandroid.features.main.MainContracts
import com.widyatama.coreandroid.features.main.MainPresenter
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

