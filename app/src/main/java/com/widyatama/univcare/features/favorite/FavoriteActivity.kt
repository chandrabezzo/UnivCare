package com.widyatama.univcare.features.favorite

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.widyatama.core.base.BaseActivity
import com.widyatama.univcare.R
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.android.ext.android.inject

class FavoriteActivity : BaseActivity(), FavoriteContracts.View {

    val presenter: FavoritePresenter<FavoriteContracts.View> by inject()

    var isFavorite = false

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)

        setSupportActionBar(toolbar)
        mActionBar = supportActionBar
        displayHome()
        setActionBarTitle(getString(R.string.app_name))
        toolbar.setNavigationOnClickListener {
            onNavigationClick()
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.activity_favorite
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_favorite, menu)
        if (isFavorite){
            menu?.findItem(R.id.nav_favorite)?.setIcon(R.drawable.ic_favorite)
        }
        else {
            menu?.findItem(R.id.nav_favorite)?.setIcon(R.drawable.ic_favorite_border)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.nav_favorite -> {
                if (isFavorite){
                    item.setIcon(R.drawable.ic_favorite_border)
                    isFavorite = false
                }
                else {
                    item.setIcon(R.drawable.ic_favorite)
                    isFavorite = true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
