package com.docotel.coreandroid.features.auth.register


import android.os.Bundle
import android.view.View
import com.docotel.core.base.BaseFragment
import com.docotel.core.extension.toast
import com.docotel.coreandroid.R
import com.docotel.coreandroid.constanta.ApiConstans
import org.koin.android.ext.android.inject

class RegisterFragment : BaseFragment(), RegisterContracts.View {

    private val presenter : RegisterPresenter<RegisterContracts.View> by inject()

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
        return R.layout.fragment_register
    }
}
