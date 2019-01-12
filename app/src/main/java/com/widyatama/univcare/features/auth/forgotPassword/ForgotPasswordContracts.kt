package com.widyatama.coreandroid.features.auth.forgotPassword

import com.widyatama.core.base.BaseDialogContract
import com.widyatama.core.base.BasePresenterContract

class ForgotPasswordContracts {
    interface View : BaseDialogContract {

    }

    interface Presenter<V : View> : BasePresenterContract<V> {

    }
}