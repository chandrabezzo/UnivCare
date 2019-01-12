package com.docotel.coreandroid.features.auth.forgotPassword

import com.docotel.core.base.BasePresenter
import com.docotel.core.data.session.SessionHelper
import com.docotel.core.util.SchedulerProviderUtil
import com.docotel.coreandroid.data.local.LocalStorageHelper
import com.docotel.coreandroid.data.network.ApiHelper
import io.reactivex.disposables.CompositeDisposable

class ForgotPasswordPresenter<V : ForgotPasswordContracts.View>
    constructor(private val apiHelper: ApiHelper, private val localHelper : LocalStorageHelper,
                sessionHelper: SessionHelper, schedulerProvider: SchedulerProviderUtil, compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(sessionHelper, schedulerProvider, compositeDisposable), ForgotPasswordContracts.Presenter<V> {
}