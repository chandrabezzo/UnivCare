package com.widyatama.univcare.adapter.viewPager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.widyatama.univcare.R
import com.widyatama.univcare.features.auth.login.LoginFragment
import com.widyatama.univcare.features.auth.register.RegisterFragment

/**
 * Created by bezzo on 08/11/17.
 */

class AuthVPAdapter(val context: Context, fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    var tabTitles: Array<String> = arrayOf(context.getString(R.string.login), context.getString(R.string.register))
    val PAGE_COUNT = tabTitles.size

    override fun getItem(position: Int): Fragment {
        val fragment = Fragment()
        when (position) {
            0 -> {
                return LoginFragment()
            }
            1 -> {
                return RegisterFragment()
            }
        }

        return fragment
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}