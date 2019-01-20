package com.widyatama.univcare.adapter.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.widyatama.core.base.BaseHolder
import com.widyatama.core.listener.OnItemClickListener
import com.widyatama.univcare.R
import com.widyatama.univcare.data.model.Country
import kotlinx.android.synthetic.main.item_rv_country.view.*


class CountryRVAdapter(val context: Context, val list: ArrayList<Country>)
    : RecyclerView.Adapter<CountryRVAdapter.Item>() {

    lateinit var listener: OnItemClickListener

    fun setOnClick(listener: OnItemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_country, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.model = list[position]
    }

    fun setItem(values: ArrayList<Country>){
        list.clear()
        list.addAll(values)
    }

    inner class Item(itemView: View): BaseHolder<Country>(itemView){

        init {
            itemView.setOnClickListener { listener.onItemClick(it, layoutPosition) }
        }

        override fun setContent(model: Country) {
            itemView.tv_country.text = model.name
        }
    }
}