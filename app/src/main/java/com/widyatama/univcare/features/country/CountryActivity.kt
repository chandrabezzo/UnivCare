package com.widyatama.univcare.features.country

import android.os.Bundle
import com.widyatama.core.base.BaseActivity
import com.widyatama.univcare.R
import org.koin.android.ext.android.inject

class CountryActivity : BaseActivity(), CountryContracts.View {

    val presenter: CountryPresenter<CountryContracts.View> by inject()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.activity_country
    }
}
