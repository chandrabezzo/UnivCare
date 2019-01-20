package com.widyatama.univcare.features.campusType

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.widyatama.core.base.BaseActivity
import com.widyatama.core.listener.OnItemClickListener
import com.widyatama.univcare.R
import com.widyatama.univcare.adapter.recyclerView.CampusTypeRVAdapter
import com.widyatama.univcare.data.model.CampusType
import kotlinx.android.synthetic.main.activity_campus_type.*
import org.koin.android.ext.android.inject

class CampusTypeActivity : BaseActivity(), CampusTypeContracts.View {

    val presenter: CampusTypePresenter<CampusTypeContracts.View> by inject()
    val adapter: CampusTypeRVAdapter by inject()
    val list = ArrayList<CampusType>()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)

        val layoutManager = GridLayoutManager(this, 2)
        rv_campus_type.layoutManager = layoutManager
        rv_campus_type.adapter = adapter
        presenter.getTypes()

        adapter.setOnItemClick(object : OnItemClickListener{
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
        return R.layout.activity_campus_type
    }

    override fun showTypes(values: ArrayList<CampusType>) {
        adapter.setItem(values)
        adapter.notifyDataSetChanged()

        list.clear()
        list.addAll(values)
    }
}
