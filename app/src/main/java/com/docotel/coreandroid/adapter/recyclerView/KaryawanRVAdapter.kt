package com.docotel.coreandroid.adapter.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.docotel.core.base.BaseHolder
import com.docotel.coreandroid.R
import com.docotel.coreandroid.data.model.Karyawan
import kotlinx.android.synthetic.main.item_rv_sample.view.*

/**
 * Created by bezzo on 11/01/18.
 * Change String to model you need convert to recycler view
 */
class KaryawanRVAdapter(var context : Context,
                        var list : ArrayList<Karyawan>)
    : RecyclerView.Adapter<KaryawanRVAdapter.Item>() {

    // uncomment if you use click listener
//    lateinit var listener : OnItemClickListener
//
//    fun setOnItemClickListener(listener: OnItemClickListener){
//        this.listener = listener
//    }

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


    inner class Item(itemView : View) : BaseHolder<Karyawan>(itemView){

        init {
            // add listener if you need
//            itemView.setOnClickListener {
//                listener.onItemClick(it, layoutPosition)
//            }
//
//            itemView.setOnLongClickListener {
//                listener.onItemLongClick(it, layoutPosition)
//            }
        }

        override fun setContent(model: Karyawan) {
            itemView.tv_item_rv.text = model.nama
        }
    }
}