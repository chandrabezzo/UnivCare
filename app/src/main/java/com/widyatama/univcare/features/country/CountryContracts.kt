package com.widyatama.univcare.features.country

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract
import com.widyatama.univcare.data.model.Country

class CountryContracts {
    interface View: BaseActivityContract {
        fun showCountries(values: ArrayList<Country>)
    }

    interface Presenter<V: View>: BasePresenterContract<V> {
        fun getCountries()

        fun getCountry(keyword: String)
    }
}