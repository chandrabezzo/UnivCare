package com.docotel.coreandroid.features.auth.login

import com.docotel.core.base.BaseFragmentContract
import com.docotel.core.base.BasePresenterContract

class LoginContracts {
    interface View : BaseFragmentContract {

    }

    interface Presenter<V : View> : BasePresenterContract<V> {

    }
}