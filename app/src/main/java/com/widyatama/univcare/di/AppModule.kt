
package com.widyatama.univcare.di

import com.google.gson.Gson
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.univcare.adapter.recyclerView.CampusTypeRVAdapter
import com.widyatama.univcare.adapter.recyclerView.CountryRVAdapter
import com.widyatama.univcare.adapter.recyclerView.KaryawanRVAdapter
import com.widyatama.univcare.adapter.recyclerView.UniversityRVAdapter
import com.widyatama.univcare.adapter.spinner.JabatanSPAdapter
import com.widyatama.univcare.data.local.LocalStorageHelper
import com.widyatama.univcare.data.network.ApiHelper
import com.widyatama.univcare.features.campusType.CampusTypeContracts
import com.widyatama.univcare.features.campusType.CampusTypePresenter
import com.widyatama.univcare.features.country.CountryContracts
import com.widyatama.univcare.features.country.CountryPresenter
import com.widyatama.univcare.features.favorite.FavoriteContracts
import com.widyatama.univcare.features.favorite.FavoritePresenter
import com.widyatama.univcare.features.filter.FilterContract
import com.widyatama.univcare.features.filter.FilterPresenter
import com.widyatama.univcare.features.home.HomeContract
import com.widyatama.univcare.features.home.HomePresenter
import com.widyatama.univcare.features.list.ListContract
import com.widyatama.univcare.features.list.ListPresenter
import com.widyatama.univcare.features.searchCampus.SearchCampusContracts
import com.widyatama.univcare.features.searchCampus.SearchCampusPresenter
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {
    single { LocalStorageHelper(androidApplication()) }
    single { SessionHelper() }
    factory { CompositeDisposable() }
    single { Gson() }
    single { SchedulerProviderUtil() }
    single { ApiHelper(get()) }
}

val presenterModule = module {
    factory { CountryPresenter<CountryContracts.View>(get(), get(), get(), get()) }
    factory { CampusTypePresenter<CampusTypeContracts.View>(get(), get(), get(), get()) }
    factory { FavoritePresenter<FavoriteContracts.View>(get(), get(), get(), get(), get()) }
    factory { SearchCampusPresenter<SearchCampusContracts.View>(get(), get(), get(), get()) }
    factory { HomePresenter<HomeContract.View>(get(), get(), get(), get()) }
    factory { FilterPresenter<FilterContract.View>(get(), get(), get(), get()) }
    factory { ListPresenter<ListContract.View>(get(), get(), get(), get(), get()) }
}

val rvAdapterModule = module {
    factory { KaryawanRVAdapter(get(), ArrayList()) }
    factory { CountryRVAdapter(androidContext(), ArrayList()) }
    factory { CampusTypeRVAdapter(ArrayList()) }
    factory { UniversityRVAdapter(get(), ArrayList()) }
}

val spAdapterModule = module {
    factory { JabatanSPAdapter(get(), ArrayList()) }
}

val allModule = listOf(appModule, presenterModule, rvAdapterModule, spAdapterModule)


