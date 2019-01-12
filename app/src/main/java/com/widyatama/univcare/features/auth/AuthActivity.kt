package com.widyatama.univcare.features.auth

import android.os.Bundle
import com.widyatama.core.base.BaseActivity
import com.widyatama.core.extension.launchFragment
import com.widyatama.univcare.R
import com.widyatama.univcare.constanta.ApiConstans
import com.widyatama.univcare.features.auth.login.LoginFragment
import com.widyatama.univcare.features.auth.register.RegisterFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_auth.*
import org.koin.android.ext.android.inject

class AuthActivity : BaseActivity(), AuthContracts.View {

    val presenter : AuthPresenter<AuthContracts.View> by inject()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)

        launchFragment(R.id.fl_auth, LoginFragment::class.java)

        tl_auth.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> launchFragment(R.id.fl_auth, LoginFragment::class.java){
                        putString(ApiConstans.DATA, "kiriman AuthActivity select tab")
                    }
                    1 -> launchFragment(R.id.fl_auth, RegisterFragment::class.java){
                        putString(ApiConstans.DATA, "kiriman AuthActivity select tab")
                    }
                }
            }

        })
    }

    override fun setLayout(): Int {
        return R.layout.activity_auth
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
