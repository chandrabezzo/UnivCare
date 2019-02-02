package com.widyatama.univcare.adapter.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.widyatama.core.base.BaseHolder
import com.widyatama.core.listener.OnItemClickListener
import com.widyatama.univcare.R
import com.widyatama.univcare.data.model.UniversityResponse
import kotlinx.android.synthetic.main.item_rv_sample.view.*


/**
 * Created by iman on 29/01/2019.
 */

class UniversityRVAdapter(var context : Context,
                          var list : ArrayList<UniversityResponse.University>)
    : RecyclerView.Adapter<UniversityRVAdapter.Item>() {

    lateinit var listener : OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.model = list[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv_sample, parent, false))
    }


    inner class Item(itemView : View) : BaseHolder<UniversityResponse.University>(itemView){

        init {
            // add listener if you need
            itemView.setOnClickListener {
                listener.onItemClick(it, layoutPosition)
            }

            itemView.setOnLongClickListener {
                listener.onItemLongClick(it, layoutPosition)
            }
        }

        override fun setContent(model: UniversityResponse.University) {
            itemView.tv_item_rv.text = model.name
        }
    }
}