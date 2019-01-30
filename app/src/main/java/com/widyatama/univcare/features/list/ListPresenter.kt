package com.widyatama.univcare.features.list

import android.widget.Toast
import com.androidnetworking.error.ANError
import com.widyatama.core.base.BasePresenter
import com.widyatama.core.data.network.ResponseHandler
import com.widyatama.core.data.network.ResponseOkHttp
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.extension.toast
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.univcare.data.model.Karyawan
import com.widyatama.univcare.data.model.UniversityResponse
import com.widyatama.univcare.data.network.ApiHelper
import com.widyatama.univcare.features.home.HomeContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import okhttp3.Response
import java.util.concurrent.Executors


/**
 * Created by iman on 28/01/2019.
 */

class ListPresenter<V : ListContract.View> constructor(private val apiHelper: ApiHelper, sessionHelper: SessionHelper, schedulerProvider: SchedulerProviderUtil, compositeDisposable: CompositeDisposable) : BasePresenter<V>(sessionHelper, schedulerProvider, compositeDisposable), ListContract.Presenter<V> {
    override fun getUniv(name: String, country: String) {
        view?.showLoadMore()
        apiHelper.getUniv(name, country)
                .getAsOkHttpResponseAndObjectList(UniversityResponse.University::class.java, object : ResponseOkHttp<List<UniversityResponse.University>>(200) {
                    override fun onSuccess(response: Response, model: List<UniversityResponse.University>) {
                        view?.hideLoadMore()
                        view?.showUniv(model)
                    }

                    override fun onUnauthorized() {
                        view?.hideLoadMore()
                        logout()
                    }

                    override fun onFailed(response: Response) {
                        view?.hideLoadMore()
                        logging(response.message())
                    }

                    override fun onHasError(error: ANError) {
                        view?.hideLoadMore()
                        handleApiError(error)
                    }

                })
    }


}