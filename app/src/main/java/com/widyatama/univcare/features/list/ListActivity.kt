package com.widyatama.univcare.features.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.widyatama.core.base.BaseActivity
import com.widyatama.core.extension.launchActivity
import com.widyatama.core.listener.OnItemClickListener
import com.widyatama.core.listener.OnLoadMoreListener
import com.widyatama.univcare.R
import com.widyatama.univcare.adapter.recyclerView.UniversityRVAdapter
import com.widyatama.univcare.constanta.ApiConstans
import com.widyatama.univcare.data.model.UniversityResponse
import com.widyatama.univcare.features.webview.WebViewActivity
import kotlinx.android.synthetic.main.activity_list.*
import org.koin.android.ext.android.inject
import java.util.*

class ListActivity : BaseActivity(), ListContract.View {

    val presenter : ListPresenter<ListContract.View> by inject()

    lateinit var rvUniversity : UniversityRVAdapter
    var listUniversity = ArrayList<UniversityResponse.University>()
    var listUniversityTmp = ArrayList<UniversityResponse.University>()
    val linearLayoutManager = LinearLayoutManager(this)

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        setActionBarTitle(getString(R.string.app_name))

        rvUniversity = UniversityRVAdapter(this, listUniversity)
        initRecyclerView()

        val filter = dataReceived?.getInt(ApiConstans.FILTER)
        val search = dataReceived?.getString(ApiConstans.SEARCH)
        if (filter != null){
            when(filter){
                1 -> presenter.getUniv("institut", "indonesia")
                2 -> presenter.getUniv("universitas", "")
                3 -> presenter.getUniv("politeknik", "")
            }
        }
        if (search != null){
            presenter.getUniv(search, "")
        }

    }

    override fun setLayout(): Int {
        return R.layout.activity_list
    }

    fun initRecyclerView() {
        rv_list.layoutManager = linearLayoutManager
        rv_list.adapter = rvUniversity
    }

    override fun showUniv(univ: List<UniversityResponse.University>) {
        System.out.println("added =================" + univ)
        listUniversity.clear()
        listUniversity.addAll(univ)
        rvUniversity.notifyDataSetChanged()
        rvUniversity.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemLongClick(itemView: View, position: Int): Boolean {
                return false
            }

            override fun onItemClick(itemView: View, position: Int) {
                val item = listUniversity.get(position)
                launchActivity<WebViewActivity>(false){
                    putExtra(ApiConstans.DATA, item.webPages?.get(0))
                }


            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_navigation, menu)
        val searchItem = menu?.findItem(R.id.nav_search)

        val searchView: SearchView? = searchItem?.actionView as SearchView

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isEmpty()) {
                    presenter.getUniv(query, "")
                }
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (!query.isEmpty()) {
                    presenter.getUniv(query, "")
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

    fun filterLocal(query : String){
        listUniversityTmp.addAll(listUniversity)
        listUniversity.clear()
        if (query.length == 0){
            listUniversity.addAll(listUniversityTmp)
        } else {
            listUniversityTmp.forEach {
                if (it.name?.toLowerCase(Locale.getDefault())?.contains(query)!!)
                    listUniversity.add(it)
            }
        }
        rvUniversity.notifyDataSetChanged()

    }


    override fun showLoadMore() {
        pb_load_more.visibility = View.VISIBLE
        rv_list.visibility = View.GONE
    }

    override fun hideLoadMore() {
        pb_load_more.visibility = View.GONE
        rv_list.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

}
