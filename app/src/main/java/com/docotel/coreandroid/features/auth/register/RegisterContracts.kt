package com.docotel.coreandroid.features.auth.register

import com.docotel.core.base.BaseFragmentContract
import com.docotel.core.base.BasePresenterContract

class RegisterContracts {
    interface View : BaseFragmentContract {

    }

    interface Presenter<V : View> : BasePresenterContract<V> {

    }
}