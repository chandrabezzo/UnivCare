package com.widyatama.univcare.features.filter

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract
import com.widyatama.univcare.data.model.UniversityResponse


/**
 * Created by iman on 22/01/2019.
 */

class FilterContract {
    interface View : BaseActivityContract{
        fun showUniv(univ : List<UniversityResponse.University>)

        fun showLoadMore()

        fun hideLoadMore()
    }
    interface Presenter<V:View> : BasePresenterContract<V>{
        fun getUniv()
    }
}