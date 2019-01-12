
package com.widyatama.univcare.di

import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.univcare.adapter.recyclerView.KaryawanRVAdapter
import com.widyatama.univcare.adapter.spinner.JabatanSPAdapter
import com.widyatama.univcare.data.local.LocalStorageHelper
import com.widyatama.univcare.data.network.ApiHelper
import com.widyatama.univcare.features.auth.AuthContracts
import com.widyatama.univcare.features.auth.AuthPresenter
import com.widyatama.univcare.features.auth.forgotPassword.ForgotPasswordContracts
import com.widyatama.univcare.features.auth.forgotPassword.ForgotPasswordPresenter
import com.widyatama.univcare.features.auth.login.LoginContracts
import com.widyatama.univcare.features.auth.login.LoginPresenter
import com.widyatama.univcare.features.auth.register.RegisterContracts
import com.widyatama.univcare.features.auth.register.RegisterPresenter
import com.widyatama.univcare.features.main.MainContracts
import com.widyatama.univcare.features.main.MainPresenter
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


