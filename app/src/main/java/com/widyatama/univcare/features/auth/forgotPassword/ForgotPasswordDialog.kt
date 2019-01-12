package com.widyatama.coreandroid.features.auth.forgotPassword


import android.os.Bundle
import android.view.View
import com.widyatama.core.base.BaseDialogFragment
import com.widyatama.core.extension.toast
import com.widyatama.coreandroid.R
import com.widyatama.coreandroid.constanta.ApiConstans
import org.koin.android.ext.android.inject

class ForgotPasswordDialog : BaseDialogFragment(), ForgotPasswordContracts.View {

    private val presenter : ForgotPasswordPresenter<ForgotPasswordContracts.View> by inject()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.toast(dataReceived?.getString(ApiConstans.DATA, "") ?: "kosong")
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.dialog_forgot_password
    }
}
