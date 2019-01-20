package com.widyatama.univcare.features.searchCampus

import android.os.Bundle
import com.widyatama.core.base.BaseDialogFragment
import com.widyatama.univcare.R
import org.koin.android.ext.android.inject

class SearchCampusActivity : BaseDialogFragment(), SearchCampusContracts.View {

    val presenter: SearchCampusPresenter<SearchCampusContracts.View> by inject()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.dialog_search_campus
    }
}
