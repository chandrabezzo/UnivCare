package com.widyatama.univcare.features.main

import android.widget.Toast
import com.androidnetworking.error.ANError
import com.widyatama.core.base.BasePresenter
import com.widyatama.core.data.network.ResponseHandler
import com.widyatama.core.data.network.ResponseOkHttp
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.extension.toast
import com.widyatama.core.util.AppLoggerUtil
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.univcare.data.local.LocalStorageHelper
import com.widyatama.univcare.data.model.*
import com.widyatama.univcare.data.network.ApiHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import okhttp3.Response
import java.util.concurrent.Executors


/**
 * Created by bezzo on 24/01/18.
 * if you use kotlin, when send to view you must add "?" for null check pointer
 * but if you use java, when send to view you must add if(!isViewAttached) return;
 * before you send data to view
 */

class MainPresenter<V : MainContracts.View>
constructor(private val apiHelper: ApiHelper, private val localHelper: LocalStorageHelper,
            sessionHelper: SessionHelper, schedulerProvider: SchedulerProviderUtil, compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(sessionHelper, schedulerProvider, compositeDisposable), MainContracts.Presenter<V> {

    override fun getKaryawanApi() {
        apiHelper.getKaryawan("1", "15")
                .getAsOkHttpResponseAndObjectList(Karyawan::class.java, object : ResponseOkHttp<List<Karyawan>>(200) {
                    override fun onSuccess(response: Response, model: List<Karyawan>) {
                        val exec = Executors.newSingleThreadExecutor()
                        exec.execute {
                            localHelper.sampleDatabase.karyawan().deleteAll()
                            localHelper.sampleDatabase.karyawan()
                                    .inserts(model)
                        }

                        getAllKaryawan()
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

    override fun getAllKaryawan() {
        compositeDisposable.add(localHelper.sampleDatabase.karyawan()
                .getAll().compose(schedulerProvider.ioToMainFlowableScheduler())
                .subscribe({
                    view?.hideRefreshing()
                    view?.showKaryawan(it)
                }, {
                    logging(it.toString())
                }))
    }

    override fun loadMoreKaryawan(limit: Int) {
        apiHelper.getKaryawan("1", limit.toString())
                .getAsOkHttpResponseAndObjectList(Karyawan::class.java, object : ResponseOkHttp<List<Karyawan>>(200) {
                    override fun onSuccess(response: Response, model: List<Karyawan>) {
                        val exec = Executors.newSingleThreadExecutor()
                        exec.execute {
                            localHelper.sampleDatabase.karyawan().deleteAll()
                            localHelper.sampleDatabase.karyawan()
                                    .inserts(model)
                        }

                        view?.hideLoadMore()
                        getAllKaryawan()
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

    override fun getUserLocal() {
        localHelper.sampleDatabase.user().get(1)
                .compose(schedulerProvider.ioToMainFlowableScheduler())
                .subscribe({
                    view?.showUser(it)
                }, {
                    AppLoggerUtil.e(it.toString())
                })
    }

    override fun getJabatanApi() {
        compositeDisposable.add(apiHelper.getJabatan()
                .subscribe(object : ResponseHandler<JabatanResponse>(200){
                    override fun onSuccess(model: JabatanResponse) {
                        val exec = Executors.newSingleThreadExecutor()
                        exec.execute {
                            localHelper.sampleDatabase.jabatan().deleteAll()
                            localHelper.sampleDatabase.jabatan()
                                    .inserts(model.data!!)
                        }

                        getAllJabatan()
                    }

                    override fun onUnauthorized() {
                        logout()
                    }

                    override fun onError(model: JabatanResponse) {
                        logging(model.message)
                    }

                }, Consumer {
                   if (it is ANError) {
                       handleApiError(it)
                   }
                }))
    }

    override fun getAllJabatan() {
        compositeDisposable.add(localHelper.sampleDatabase.jabatan()
                .getAll().compose(schedulerProvider.ioToMainFlowableScheduler())
                .subscribe({
                    view?.showJabatan(it)
                }, {
                    logging(it.toString())
                }))
    }

    override fun getUserApi() {
        compositeDisposable.add(apiHelper.getUser()
                .subscribe(object : ResponseHandler<UserResponse>(200){
                    override fun onSuccess(model: UserResponse) {
                        val exec = Executors.newSingleThreadExecutor()
                        exec.execute {
                            model.data?.get(0)?.let {
                                localHelper.sampleDatabase.user().deleteAll()
                                localHelper.sampleDatabase.user().insert(it)
                            }
                        }

                        getUserLocal()
                    }

                    override fun onUnauthorized() {
                        logout()
                    }

                    override fun onError(model: UserResponse) {
                        logging(model.message)
                    }

                }, Consumer {
                    if (it is ANError){
                        handleApiError(it)
                    }
                }))
    }

    override fun getSocmedApi() {
        apiHelper.getSocmed()
                .getAsOkHttpResponseAndObject(Socmed::class.java, object : ResponseOkHttp<Socmed>(200){
                    override fun onSuccess(response: Response, model: Socmed) {
                        val exec = Executors.newSingleThreadExecutor()
                        exec.execute {
                            localHelper.sampleDatabase.socmed().deleteAll()
                            localHelper.sampleDatabase.socmed()
                                    .insert(model)
                        }

                        getSocmed()
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

    override fun getSocmed() {
        compositeDisposable.add(localHelper.sampleDatabase.socmed().get()
                .compose(schedulerProvider.ioToMainFlowableScheduler())
                .subscribe({
                    view?.showSocmed(it)
                }, {
                    logging(it.message)
                }))
    }

    override fun addKaryawan(value: Karyawan) {
        apiHelper.addKaryawan(value)
                .getAsOkHttpResponseAndObject(Karyawan::class.java, object : ResponseOkHttp<Karyawan>(201){
                    override fun onSuccess(response: Response, model: Karyawan) {
                        view?.getContext()?.toast("yeah", Toast.LENGTH_SHORT)
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