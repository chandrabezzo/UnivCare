package com.widyatama.univcare.features.home

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract
import com.widyatama.univcare.data.model.UniversityResponse

class HomeContract {
    interface View : BaseActivityContract{

        fun showUniv(univ : List<UniversityResponse.University>)

        fun showLoadMore()

        fun hideLoadMore()

    }

    interface Presenter<V : View> : BasePresenterContract<V>{
        fun getUniv(name: String, country: String)
    }
}