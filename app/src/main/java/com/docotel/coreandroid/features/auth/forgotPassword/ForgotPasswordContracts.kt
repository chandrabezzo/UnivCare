package com.docotel.coreandroid.features.auth.forgotPassword

import com.docotel.core.base.BaseDialogContract
import com.docotel.core.base.BasePresenterContract

class ForgotPasswordContracts {
    interface View : BaseDialogContract {

    }

    interface Presenter<V : View> : BasePresenterContract<V> {

    }
}