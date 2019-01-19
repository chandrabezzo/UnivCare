package com.widyatama.univcare.features.country

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract

class CountryContracts {
    interface View: BaseActivityContract {

    }

    interface Presenter<V: View>: BasePresenterContract<V> {

    }
}