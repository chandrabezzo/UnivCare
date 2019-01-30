package com.widyatama.univcare.features.list

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract
import com.widyatama.univcare.data.model.UniversityResponse


/**
 * Created by iman on 28/01/2019.
 */

class ListContract {
    interface View : BaseActivityContract {

        fun showUniv(univ : List<UniversityResponse.University>)

        fun showLoadMore()

        fun hideLoadMore()

    }

    interface Presenter<V : View> : BasePresenterContract<V> {
        fun getUniv(name: String, country: String)
    }
}