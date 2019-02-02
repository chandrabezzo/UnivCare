package com.widyatama.univcare.features.country

import com.androidnetworking.error.ANError
import com.widyatama.core.base.BasePresenter
import com.widyatama.core.data.network.ResponseOkHttp
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.univcare.data.model.Country
import com.widyatama.univcare.data.network.ApiHelper
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Response

class CountryPresenter<V: CountryContracts.View>
    constructor(private val apiHelper: ApiHelper, sessionHelper: SessionHelper, schedulerProvider: SchedulerProviderUtil,
                compositeDisposable: CompositeDisposable) : BasePresenter<V>(sessionHelper, schedulerProvider, compositeDisposable),
        CountryContracts.Presenter<V> {

    override fun getCountries() {
        apiHelper.getCountries().getAsOkHttpResponseAndObjectList(Country::class.java,
                object : ResponseOkHttp<ArrayList<Country>>(200){
                    override fun onSuccess(response: Response, model: ArrayList<Country>) {
                        view?.showCountries(model)
                    }

                    override fun onUnauthorized() {
                        logout()
                    }

                    override fun onFailed(response: Response) {
                        logging(response.message())
                    }

                    override fun onHasError(error: ANError) {
                        handleApiError(error)
                    }

                })
    }

    override fun getCountry(keyword: String) {
        apiHelper.getCountry(keyword).getAsOkHttpResponseAndObjectList(Country::class.java,
                object : ResponseOkHttp<ArrayList<Country>>(200){
                    override fun onSuccess(response: Response, model: ArrayList<Country>) {
                        view?.showCountries(model)
                    }

                    override fun onUnauthorized() {
                        logout()
                    }

                    override fun onFailed(response: Response) {
                        logging(response.message())
                    }

                    override fun onHasError(error: ANError) {
                        handleApiError(error)
                    }

                })
    }
}