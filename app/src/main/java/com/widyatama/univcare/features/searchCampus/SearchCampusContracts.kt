package com.widyatama.univcare.features.searchCampus

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BaseDialogContract
import com.widyatama.core.base.BasePresenterContract

class SearchCampusContracts {
    interface View: BaseDialogContract {

    }

    interface Presenter<V: View>: BasePresenterContract<V> {

    }
}