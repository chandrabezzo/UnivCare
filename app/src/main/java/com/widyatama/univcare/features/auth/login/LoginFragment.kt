package com.widyatama.univcare.features.auth.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.widyatama.core.base.BaseFragment
import com.widyatama.core.extension.launchActivity
import com.widyatama.core.extension.launchDialog
import com.widyatama.core.extension.toast
import com.widyatama.univcare.R
import com.widyatama.univcare.constanta.ApiConstans
import com.widyatama.univcare.features.auth.forgotPassword.ForgotPasswordDialog
import com.widyatama.univcare.features.main.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 *
 */
class LoginFragment : BaseFragment(), LoginContracts.View {

    private val presenter : LoginPresenter<LoginContracts.View> by inject()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.toast(dataReceived?.getString(ApiConstans.DATA, "") ?: "kosong")

        tv_forgot_password.setOnClickListener {
            launchDialog(ForgotPasswordDialog::class.java){
                putString(ApiConstans.DATA, "Kiriman dari Login Fragment")
            }
        }

        btn_login.setOnClickListener {
            launchActivity<MainActivity>()
        }
    }

    override fun setLayout(): Int {
        return R.layout.fragment_login
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
