package com.widyatama.univcare.features.favorite

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.univcare.data.local.LocalStorageHelper
import com.widyatama.univcare.data.network.ApiHelper
import com.widyatama.univcare.di.presenterModule
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executors

class FavoritePresenter<V: FavoriteContracts.View>
constructor(private val apiHelper: ApiHelper, private val localHelper: LocalStorageHelper,
            sessionHelper: SessionHelper, schedulerProvider: SchedulerProviderUtil,
            compositeDisposable: CompositeDisposable) : BasePresenter<V>(sessionHelper, schedulerProvider,
        compositeDisposable), FavoriteContracts.Presenter<V> {

    override fun getAllFavorite() {
        compositeDisposable.add(localHelper.sampleDatabase.university().getAll()
                .compose(schedulerProvider.ioToMainFlowableScheduler())
                .subscribe({
                    view?.showAll(it)
                }, {
                    logging(it.toString())
                }))
    }

    override fun deleteFavorite(id: String) {
        val exec = Executors.newSingleThreadExecutor()
        exec.execute {
            localHelper.sampleDatabase.university()
                    .delete(id)
        }
        getAllFavorite()
    }
}