package com.widyatama.univcare.features.favorite

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.widyatama.core.base.BaseActivity
import com.widyatama.core.extension.launchActivity
import com.widyatama.core.listener.OnItemClickListener
import com.widyatama.univcare.R
import com.widyatama.univcare.adapter.recyclerView.UniversityRVAdapter
import com.widyatama.univcare.constanta.ApiConstans
import com.widyatama.univcare.data.model.UniversityResponse
import com.widyatama.univcare.features.webview.WebViewActivity
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.android.ext.android.inject
import java.util.ArrayList

class FavoriteActivity : BaseActivity(), FavoriteContracts.View {

    val presenter: FavoritePresenter<FavoriteContracts.View> by inject()
    var isFavorite = false
    lateinit var rvUniversity : UniversityRVAdapter
    var listUniversity = ArrayList<UniversityResponse.University>()
    val linearLayoutManager = LinearLayoutManager(this)

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)

        setSupportActionBar(toolbar)
        mActionBar = supportActionBar
        displayHome()
        setActionBarTitle(getString(R.string.favorite))
        toolbar.setNavigationOnClickListener {
            onNavigationClick()
        }

        rvUniversity = UniversityRVAdapter(this, listUniversity)
        rv_favorite.layoutManager = linearLayoutManager
        rv_favorite.adapter = rvUniversity
        presenter.getAllFavorite()

        rvUniversity.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemLongClick(itemView: View, position: Int): Boolean {
                val favoriteDialog = AlertDialog.Builder(this@FavoriteActivity)
                favoriteDialog.setTitle(getString(R.string.favorite))
                favoriteDialog.setMessage(getString(R.string.hapus_dari_favorite))
                favoriteDialog.setPositiveButton("Hapus") { _, _ ->
                    listUniversity[position].name?.let {name ->
                        presenter.deleteFavorite(name)
                    }
                }
                favoriteDialog.setNegativeButton("Batal") { dialog, which ->
                    dialog.dismiss()
                }
                favoriteDialog.show()
                return true
                return false
            }

            override fun onItemClick(itemView: View, position: Int) {
                val item = listUniversity.get(position)
                launchActivity<WebViewActivity>(true) {
                    putExtra(ApiConstans.DATA, item.webPages?.get(0))
                }
            }
        })

        sr_favorite.setOnRefreshListener {
            presenter.getAllFavorite()
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.activity_favorite
    }

    override fun showAll(values: List<UniversityResponse.University>) {
        listUniversity.clear()
        listUniversity.addAll(values)
        rvUniversity.notifyDataSetChanged()

        sr_favorite.isRefreshing = false
    }
}
