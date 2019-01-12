package com.widyatama.univcare.features.auth.register

import com.widyatama.core.base.BaseFragmentContract
import com.widyatama.core.base.BasePresenterContract

class RegisterContracts {
    interface View : BaseFragmentContract {

    }

    interface Presenter<V : View> : BasePresenterContract<V> {

    }
}