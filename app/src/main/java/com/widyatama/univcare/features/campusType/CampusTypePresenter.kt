package com.widyatama.univcare.features.campusType

import com.widyatama.core.base.BasePresenter
import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.univcare.R
import com.widyatama.univcare.data.model.CampusType
import com.widyatama.univcare.data.network.ApiHelper
import io.reactivex.disposables.CompositeDisposable

class CampusTypePresenter<V: CampusTypeContracts.View>
constructor(private val apiHelper: ApiHelper, sessionHelper: SessionHelper, schedulerProvider: SchedulerProviderUtil,
            compositeDisposable: CompositeDisposable) : BasePresenter<V>(sessionHelper, schedulerProvider, compositeDisposable),
        CampusTypeContracts.Presenter<V> {

    override fun getTypes() {
        val types = ArrayList<CampusType>()

        view?.getContext()?.let { context ->
            val university = CampusType(1, context.getString(R.string.universitas),
                    R.drawable.ic_college_graduation)

            val politeknik = CampusType(2, context.getString(R.string.politeknik),
                    R.drawable.ic_college_graduation)

            val institute = CampusType(3, context.getString(R.string.institut),
                    R.drawable.ic_college_graduation)

            types.add(university)
            types.add(politeknik)
            types.add(institute)

            view?.showTypes(types)
        }
    }
}