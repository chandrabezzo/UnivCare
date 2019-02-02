package com.widyatama.univcare.features.favorite

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract
import com.widyatama.univcare.data.model.UniversityResponse

class FavoriteContracts {
    interface View: BaseActivityContract {
        fun showAll(values: List<UniversityResponse.University>)
    }

    interface Presenter<V: View>: BasePresenterContract<V> {
        fun getAllFavorite()

        fun deleteFavorite(id: String)
    }
}