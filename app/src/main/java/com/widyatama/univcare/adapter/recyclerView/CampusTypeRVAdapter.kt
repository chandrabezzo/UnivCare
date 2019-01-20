package com.widyatama.univcare.adapter.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.widyatama.core.base.BaseHolder
import com.widyatama.core.listener.OnItemClickListener
import com.widyatama.univcare.R
import com.widyatama.univcare.data.model.CampusType
import kotlinx.android.synthetic.main.item_rv_campus_type.view.*

class CampusTypeRVAdapter(val list: ArrayList<CampusType>)
    : RecyclerView.Adapter<CampusTypeRVAdapter.Item>(){

    lateinit var listener: OnItemClickListener

    fun setOnItemClick(listener: OnItemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_campus_type,
                parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.model = list[position]
    }

    fun setItem(values: ArrayList<CampusType>){
        list.clear()
        list.addAll(values)
    }

    inner class Item(itemView: View): BaseHolder<CampusType>(itemView){
        override fun setContent(model: CampusType) {
            itemView.iv_campus_type.setImageResource(model.image)
            itemView.tv_campus_type.text = model.name
        }
    }
}