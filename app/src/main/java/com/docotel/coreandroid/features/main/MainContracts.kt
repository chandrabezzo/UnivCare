package com.docotel.coreandroid.features.main

import com.docotel.core.base.BaseActivityContract
import com.docotel.core.base.BasePresenterContract
import com.docotel.coreandroid.data.model.JabatanResponse
import com.docotel.coreandroid.data.model.Karyawan
import com.docotel.coreandroid.data.model.Socmed
import com.docotel.coreandroid.data.model.UserResponse

class MainContracts {

    interface View : BaseActivityContract {
        fun showUser(user : UserResponse.User)

        fun showJabatan(jabatan : List<JabatanResponse.Jabatan>)

        fun showLoadMore()

        fun hideLoadMore()

        fun showKaryawan(values : List<Karyawan>)

        fun hideRefreshing()

        fun showSocmed(value : Socmed)
    }

    interface Presenter<V : View> : BasePresenterContract<V> {
        fun getUserApi()

        fun getUserLocal()

        fun getJabatanApi()

        fun getAllJabatan()

        fun getKaryawanApi()

        fun getAllKaryawan()

        fun loadMoreKaryawan(limit : Int)

        fun getSocmedApi()

        fun getSocmed()

        fun addKaryawan(value : Karyawan)
    }
}