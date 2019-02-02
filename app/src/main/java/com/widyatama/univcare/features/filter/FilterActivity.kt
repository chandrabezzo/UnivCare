package com.widyatama.univcare.features.filter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.widyatama.core.base.BaseActivity
import com.widyatama.core.extension.launchActivity
import com.widyatama.univcare.R
import com.widyatama.univcare.constanta.ApiConstans
import com.widyatama.univcare.data.model.UniversityResponse
import com.widyatama.univcare.features.country.CountryActivity
import com.widyatama.univcare.features.list.ListActivity
import kotlinx.android.synthetic.main.activity_filter.*
import org.koin.android.ext.android.inject

class FilterActivity : BaseActivity(), FilterContract.View{

    val presenter : FilterPresenter<FilterContract.View> by inject()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        setActionBarTitle(getString(R.string.filter))
        btnInstitute.setOnClickListener {
            launchActivity<ListActivity>(false){
                putExtra(ApiConstans.FILTER, 1)
            }
        }
        btnUniv.setOnClickListener {
            launchActivity<ListActivity>(false){
                putExtra(ApiConstans.FILTER, 2)
            }
        }
        btnPoliteknik.setOnClickListener {
            launchActivity<ListActivity>(false){
                putExtra(ApiConstans.FILTER, 3)
            }
        }
        btnCountry.setOnClickListener {
            // masuk ke activity pilih country
//             launchActivity<CountryActivity>(false)
        }

    }

    override fun setLayout(): Int {
        return R.layout.activity_filter
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
