package com.docotel.coreandroid.features.auth

import com.docotel.core.base.BaseActivityContract
import com.docotel.core.base.BasePresenterContract

class AuthContracts {
    interface View : BaseActivityContract {

    }

    interface Presenter<V : View> : BasePresenterContract<V> {

    }
}