package com.widyatama.coreandroid.features.auth

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract

class AuthContracts {
    interface View : BaseActivityContract {

    }

    interface Presenter<V : View> : BasePresenterContract<V> {

    }
}