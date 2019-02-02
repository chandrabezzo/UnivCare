package com.widyatama.univcare.features.country

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.widyatama.core.base.BaseActivity
import com.widyatama.core.extension.launchActivity
import com.widyatama.core.listener.OnItemClickListener
import com.widyatama.univcare.R
import com.widyatama.univcare.adapter.recyclerView.CountryRVAdapter
import com.widyatama.univcare.constanta.ApiConstans
import com.widyatama.univcare.constanta.AppConstans
import com.widyatama.univcare.data.model.Country
import com.widyatama.univcare.features.list.ListActivity
import kotlinx.android.synthetic.main.activity_country.*
import org.koin.android.ext.android.inject

class CountryActivity : BaseActivity(), CountryContracts.View {

    val presenter: CountryPresenter<CountryContracts.View> by inject()
    val adapter: CountryRVAdapter by inject()
    val list = ArrayList<Country>()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)

        setSupportActionBar(toolbar)
        mActionBar = supportActionBar
        displayHome()
        setActionBarTitle(getString(R.string.negara))
        toolbar.setNavigationOnClickListener {
            onNavigationClick()
        }

        val layoutManager = LinearLayoutManager(this)
        rv_country.layoutManager = layoutManager
        rv_country.adapter = adapter
        presenter.getCountries()

        adapter.setOnClick(object : OnItemClickListener{
            override fun onItemClick(itemView: View, position: Int) {
                launchActivity<ListActivity> {
                    putExtra(AppConstans.COUNTRY_NAME, list[position].name)
                    putExtra(ApiConstans.FILTER, 4)
                }
            }

            override fun onItemLongClick(itemView: View, position: Int): Boolean {
                return true
            }

        })
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.activity_country
    }

    override fun showCountries(values: ArrayList<Country>) {
        list.clear()
        list.addAll(values)

        adapter.setItem(values)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_navigation, menu)
        val searchItem = menu?.findItem(R.id.nav_search)

        val searchView: SearchView? = searchItem?.actionView as SearchView

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isEmpty()) {
                    presenter.getCountry(query)
                }
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (!query.isEmpty()) {
                    presenter.getCountry(query)
                }
                return false
            }
        })
        searchView?.requestFocus()
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
