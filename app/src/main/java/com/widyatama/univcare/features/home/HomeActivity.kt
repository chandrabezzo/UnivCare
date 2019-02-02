package com.widyatama.univcare.features.home

import android.os.Bundle
import com.widyatama.core.base.BaseActivity
import com.widyatama.core.extension.launchActivity
import com.widyatama.univcare.R
import com.widyatama.univcare.data.model.UniversityResponse
import com.widyatama.univcare.features.favorite.FavoriteActivity
import com.widyatama.univcare.features.filter.FilterActivity
import com.widyatama.univcare.features.search.SearchActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject

class HomeActivity : BaseActivity(), HomeContract.View {

    val presenter : HomePresenter<HomeContract.View> by inject()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)

        btnSearch.setOnClickListener {
            launchActivity<SearchActivity>(false)
        }
        btnFilter.setOnClickListener {
            launchActivity<FilterActivity>(false)
        }
        btnFavorite.setOnClickListener {
            launchActivity<FavoriteActivity>(false)
        }
    }

    override fun setLayout(): Int {
        return R.layout.activity_home
    }

    override fun showUniv(univ: List<UniversityResponse.University>) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadMore() {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoadMore() {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
