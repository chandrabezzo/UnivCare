package com.widyatama.univcare.features.campusType

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract
import com.widyatama.univcare.data.model.CampusType

class CampusTypeContracts {
    interface View: BaseActivityContract {
        fun showTypes(values: ArrayList<CampusType>)
    }

    interface Presenter<V: View>: BasePresenterContract<V> {
        fun getTypes()
    }
}