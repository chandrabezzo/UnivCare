package com.widyatama.core.base

import com.androidnetworking.error.ANError
import com.google.gson.Gson
import com.widyatama.core.util.AppLoggerUtil
import com.widyatama.core.BuildConfig
import com.widyatama.core.CoreModul
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executors

/**
 * Created by bezzo on 26/09/17.
 */

open class BasePresenter<V : BaseViewContract>
constructor(val sessionHelper : SessionHelper,
            val schedulerProvider: SchedulerProviderUtil,
            val compositeDisposable: CompositeDisposable) : BasePresenterContract<V> {

    var view: V? = null

    val isViewAttached: Boolean
        get() = view != null

    override fun onAttach(mvpView: V) {
        view = mvpView
    }

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
    }

    override fun handleApiError(error: ANError) {
        val apiError = Gson().fromJson(error.errorBody,
                BaseResponse.Error::class.java)

        if (apiError != null) {
            if (error.errorCode == 401) {
                logout()
            } else {
                logging("[${apiError.title}] ${apiError.detail}")
                view?.handleError(6, "[${apiError.title}] ${apiError.detail}")
            }
        } else {
            logging(error.message)
            view?.handleError(6, error.message ?: "Request Error")
        }
    }

    override fun setUserAsLoggedOut() {

    }

    override fun clearLog() {
        val exec = Executors.newSingleThreadExecutor()
        exec.execute { }
    }

    override fun logout() {
        clearLog()
        CoreModul.getInstance().getCoreListener()?.onLogedOut()
    }

    companion object {
        private val TAG = "BasePresenter"
    }

    override fun logging(message: String?) {
        if (message != null && BuildConfig.DEBUG){
            AppLoggerUtil.i(message)
        }
    }
}
