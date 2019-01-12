package com.widyatama.univcare.features.auth.login

import com.widyatama.core.base.BaseFragmentContract
import com.widyatama.core.base.BasePresenterContract

class LoginContracts {
    interface View : BaseFragmentContract {

    }

    interface Presenter<V : View> : BasePresenterContract<V> {

    }
}