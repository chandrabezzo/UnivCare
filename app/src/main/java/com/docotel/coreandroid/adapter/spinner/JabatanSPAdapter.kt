package com.docotel.coreandroid.adapter.spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.docotel.coreandroid.R
import com.docotel.coreandroid.data.model.JabatanResponse
import kotlinx.android.synthetic.main.item_sp_sample.view.*

/**
 * Created by bezzo on 11/01/18.
 * Change String to model you need convert to spinner
 */
class JabatanSPAdapter constructor(var context : Context, var list : ArrayList<JabatanResponse.Jabatan>)
    : BaseAdapter(), SPAdapterContract<List<JabatanResponse.Jabatan>> {

    override fun update(values: List<JabatanResponse.Jabatan>) {
        this.clear()
        this.list.addAll(values)
        notifyDataSetChanged()
    }

    override fun clear() {
        this.list.clear()
        notifyDataSetChanged()
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        var convertView = LayoutInflater.from(context).inflate(R.layout.item_sp_sample, null)
        convertView.tv_item_sp.text = list[position].jenis
        return convertView
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }
}