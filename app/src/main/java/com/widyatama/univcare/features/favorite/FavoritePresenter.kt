package com.widyatama.univcare.features.favorite

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.univcare.data.network.ApiHelper
import io.reactivex.disposables.CompositeDisposable

class FavoritePresenter<V: FavoriteContracts.View>
constructor(private val apiHelper: ApiHelper, sessionHelper: SessionHelper, schedulerProvider: SchedulerProviderUtil,
            compositeDisposable: CompositeDisposable) : BasePresenter<V>(sessionHelper, schedulerProvider, compositeDisposable),
        FavoriteContracts.Presenter<V> {
}