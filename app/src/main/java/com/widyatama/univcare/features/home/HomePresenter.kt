package com.widyatama.univcare.features.home

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.univcare.data.network.ApiHelper
import io.reactivex.disposables.CompositeDisposable

class HomePresenter<V : HomeContract.View> constructor(private val apiHelper: ApiHelper, sessionHelper: SessionHelper, schedulerProvider: SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(sessionHelper, schedulerProvider, compositeDisposable), HomeContract.Presenter<V> {

    override fun getUniv(name: String, country: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}