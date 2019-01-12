package com.widyatama.coreandroid.features.main

import com.widyatama.core.base.BaseActivityContract
import com.widyatama.core.base.BasePresenterContract
import com.widyatama.coreandroid.data.model.JabatanResponse
import com.widyatama.coreandroid.data.model.Karyawan
import com.widyatama.coreandroid.data.model.Socmed
import com.widyatama.coreandroid.data.model.UserResponse

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