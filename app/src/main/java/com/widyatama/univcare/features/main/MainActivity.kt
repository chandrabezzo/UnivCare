package com.widyatama.coreandroid.features.main

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.widyatama.core.base.BaseActivity
import com.widyatama.core.extension.launchActivity
import com.widyatama.core.extension.snackbar
import com.widyatama.core.extension.toast
import com.widyatama.core.listener.OnLoadMoreListener
import com.widyatama.coreandroid.R
import com.widyatama.coreandroid.adapter.recyclerView.KaryawanRVAdapter
import com.widyatama.coreandroid.adapter.spinner.JabatanSPAdapter
import com.widyatama.coreandroid.data.model.JabatanResponse
import com.widyatama.coreandroid.data.model.Karyawan
import com.widyatama.coreandroid.data.model.Socmed
import com.widyatama.coreandroid.data.model.UserResponse
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), MainContracts.View {

    val presenter: MainPresenter<MainContracts.View> by inject()

    lateinit var spAdapter: JabatanSPAdapter
    var allJabatan = ArrayList<JabatanResponse.Jabatan>()
    var allKaryawan = ArrayList<Karyawan>()
    lateinit var rvAdapter: KaryawanRVAdapter
    lateinit var onLoadMoreListener: OnLoadMoreListener
    val linearLayoutManager = LinearLayoutManager(this)

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)

        setActionBarTitle(getString(R.string.beranda))
        displayHome()

        spAdapter = JabatanSPAdapter(this, allJabatan)
        sp_list.adapter = spAdapter

        rvAdapter = KaryawanRVAdapter(this, allKaryawan)

        initRecyclerView()

        presenter.getUserApi()
        presenter.getAllJabatan()
        presenter.getJabatanApi()
        presenter.getAllKaryawan()
        presenter.getKaryawanApi()
        presenter.getSocmed()
        presenter.getSocmedApi()

        sr_list.setOnRefreshListener {
            presenter.getKaryawanApi()
        }

        var karyawan = Karyawan()
        karyawan.nama = "test"

        toast(dataReceived?.getString("test", "") ?: "")

        btn_test.setOnClickListener {
            btn_test.snackbar("Test", Snackbar.LENGTH_INDEFINITE, "Ok") {
                launchActivity<MainActivity>(false) {
                    putExtra("test", "yeah")
                }
            }
        }

//        presenter.addKaryawan(karyawan)
    }

    override fun hideRefreshing() {
        if (sr_list.isRefreshing) {
            sr_list.isRefreshing = false
        }
    }

    fun initRecyclerView() {
        rv_list.layoutManager = linearLayoutManager
        rv_list.adapter = rvAdapter

        onLoadMoreListener = object : OnLoadMoreListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                onLoadMoreListener.counter = 5
                val nextPage = totalItemsCount + onLoadMoreListener.counter

                showLoadMore()
                presenter.loadMoreKaryawan(nextPage)

                view?.post {
                    rvAdapter.notifyDataSetChanged()
                }
            }
        }

        rv_list.addOnScrollListener(onLoadMoreListener)
    }

    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun showUser(user: UserResponse.User) {
        tv_user.text = user.nama + " - " + user.jabatan
        var rt = user.alamat!!.rt
        var rw = user.alamat!!.rw
        var kec = user.alamat!!.kecamatan
        var kab = user.alamat!!.kabupaten

        tv_alamat.text = "RT $rt / RW $rw, $kec, $kab"
    }

    override fun showJabatan(jabatan: List<JabatanResponse.Jabatan>) {
        spAdapter.update(jabatan)
    }

    override fun showLoadMore() {
        pb_load_more.visibility = View.VISIBLE
    }

    override fun hideLoadMore() {
        pb_load_more.visibility = View.GONE
    }

    override fun showKaryawan(values: List<Karyawan>) {
        allKaryawan.clear()
        allKaryawan.addAll(values)
        rvAdapter.notifyDataSetChanged()
    }

    override fun showSocmed(value: Socmed) {
        tv_socmed.text = value.email
    }
}
