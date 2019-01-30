package com.widyatama.univcare.features.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.widyatama.core.base.BaseActivity
import com.widyatama.core.extension.launchActivity
import com.widyatama.univcare.R
import com.widyatama.univcare.constanta.ApiConstans
import com.widyatama.univcare.constanta.AppConstans
import com.widyatama.univcare.features.list.ListActivity
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {

    override fun onInitializedView(savedInstanceState: Bundle?) {
        btnSearch.setOnClickListener {
            btnSearch.setOnClickListener {
                launchActivity<ListActivity>(false){
                    putExtra(ApiConstans.SEARCH, search.text.toString())
                }
            }
        }
    }

    override fun setLayout(): Int {
        return R.layout.activity_search
    }
}
