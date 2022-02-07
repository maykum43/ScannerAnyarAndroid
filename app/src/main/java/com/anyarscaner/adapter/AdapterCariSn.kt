package com.anyarscaner.adapter

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anyarscaner.R
import com.anyarscaner.model.SnModel

class AdapterCariSn (var data:ArrayList<SnModel>): RecyclerView.Adapter<AdapterCariSn.Holder>() {

    class Holder(view: View):RecyclerView.ViewHolder(view) {
        val tvSN = view.findViewById<TextView>(R.id.tv_sn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_sn,parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvSN.text = data[position].sn
    }

    override fun getItemCount(): Int {
        return data.size
    }
}