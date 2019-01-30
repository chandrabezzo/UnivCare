package com.widyatama.univcare.features.country

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.widyatama.core.base.BaseActivity
import com.widyatama.core.listener.OnItemClickListener
import com.widyatama.univcare.R
import com.widyatama.univcare.adapter.recyclerView.CountryRVAdapter
import com.widyatama.univcare.data.model.Country
import kotlinx.android.synthetic.main.activity_country.*
import org.koin.android.ext.android.inject

class CountryActivity : BaseActivity(), CountryContracts.View {

    val presenter: CountryPresenter<CountryContracts.View> by inject()
    val adapter: CountryRVAdapter by inject()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)

        val layoutManager = LinearLayoutManager(this)
        rv_country.layoutManager = layoutManager
        rv_country.adapter = adapter
        presenter.getCountries()

        adapter.setOnClick(object : OnItemClickListener{
            override fun onItemClick(itemView: View, position: Int) {

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
        return R.layout.activity_main
    }

    override fun showCountries(values: ArrayList<Country>) {
        adapter.setItem(values)
        adapter.notifyDataSetChanged()
    }
}
