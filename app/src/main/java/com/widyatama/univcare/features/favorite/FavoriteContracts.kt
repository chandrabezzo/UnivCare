package com.widyatama.univcare.features.favorite

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract

class FavoriteContracts {
    interface View: BaseActivityContract {

    }

    interface Presenter<V: View>: BasePresenterContract<V> {

    }
}