package com.widyatama.univcare.features.filter

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.base.BaseViewContract
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.univcare.data.network.ApiHelper
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by iman on 23/01/2019.
 */

class FilterPresenter<V : FilterContract.View> constructor(private val apiHelper: ApiHelper, sessionHelper: SessionHelper, schedulerProvider: SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(sessionHelper, schedulerProvider, compositeDisposable), FilterContract.Presenter<V> {
    override fun getUniv() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}