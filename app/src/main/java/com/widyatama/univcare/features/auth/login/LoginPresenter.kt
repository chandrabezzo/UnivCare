package com.widyatama.coreandroid.features.auth.login

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.coreandroid.data.local.LocalStorageHelper
import com.widyatama.coreandroid.data.network.ApiHelper
import io.reactivex.disposables.CompositeDisposable

class LoginPresenter<V : LoginContracts.View>
constructor(private val apiHelper : ApiHelper, private val localHelper : LocalStorageHelper,
            sessionHelper: SessionHelper, schedulerProvider: SchedulerProviderUtil, compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(sessionHelper, schedulerProvider, compositeDisposable), LoginContracts.Presenter<V> {
}