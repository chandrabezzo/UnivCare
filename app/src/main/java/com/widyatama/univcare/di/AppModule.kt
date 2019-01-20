
package com.widyatama.univcare.di

import com.google.gson.Gson
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.univcare.adapter.recyclerView.CampusTypeRVAdapter
import com.widyatama.univcare.adapter.recyclerView.CountryRVAdapter
import com.widyatama.univcare.adapter.recyclerView.KaryawanRVAdapter
import com.widyatama.univcare.adapter.spinner.JabatanSPAdapter
import com.widyatama.univcare.data.local.LocalStorageHelper
import com.widyatama.univcare.data.network.ApiHelper
import com.widyatama.univcare.features.campusType.CampusTypeContracts
import com.widyatama.univcare.features.campusType.CampusTypePresenter
import com.widyatama.univcare.features.country.CountryContracts
import com.widyatama.univcare.features.country.CountryPresenter
import com.widyatama.univcare.features.favorite.FavoriteContracts
import com.widyatama.univcare.features.favorite.FavoritePresenter
import com.widyatama.univcare.features.main.MainContracts
import com.widyatama.univcare.features.main.MainPresenter
import com.widyatama.univcare.features.searchCampus.SearchCampusContracts
import com.widyatama.univcare.features.searchCampus.SearchCampusPresenter
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
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
    factory { CountryPresenter<CountryContracts.View>(get(), get(), get(), get()) }
    factory { CampusTypePresenter<CampusTypeContracts.View>(get(), get(), get(), get()) }
    factory { FavoritePresenter<FavoriteContracts.View>(get(), get(), get(), get()) }
    factory { SearchCampusPresenter<SearchCampusContracts.View>(get(), get(), get(), get()) }
}

val rvAdapterModule = module {
    factory { KaryawanRVAdapter(get(), ArrayList()) }
    factory { CountryRVAdapter(androidContext(), ArrayList()) }
    factory { CampusTypeRVAdapter(ArrayList()) }
}

val spAdapterModule = module {
    factory { JabatanSPAdapter(get(), ArrayList()) }
}

val allModule = listOf(appModule, presenterModule, rvAdapterModule, spAdapterModule)


