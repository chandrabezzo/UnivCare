package com.docotel.core.base

import com.androidnetworking.error.ANError
import com.docotel.core.BuildConfig
import com.docotel.core.CoreModul
import com.docotel.core.data.session.SessionHelper
import com.docotel.core.util.AppLoggerUtil
import com.docotel.core.util.CommonUtil
import com.docotel.core.util.SchedulerProviderUtil
import com.google.gson.Gson
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
        if (CommonUtil.isJSONValid(error.errorBody)){
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
        else {
            if (error.toString().contains("UnknownHost")){
                view?.handleError(1, "UnknownHost")
            }
            else if (error.toString().contains("timed out") || error.toString().contains("timeout")){
                view?.handleError(2, "timed out")
            }
            else if (error.toString().contains("java") || error.toString().contains("html")){
                view?.handleError(3, "service error")
            }
            else if (error.errorBody != null) {
                if (error.errorBody.contains("html") || error.errorBody.contains("java")) {
                    view?.handleError(3, "service error")
                }
                else {
                    view?.handleError(6, error.errorBody)
                }
            }
            else {
                view?.handleError(6, error.errorDetail)
            }
        }
    }

    override fun setUserAsLoggedOut() {

    }

    override fun clearLog() {
        sessionHelper.addSession("Login", false)
        sessionHelper.deleteSession("Token")

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
