package com.anyarscaner.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.anyarscaner.R
import com.anyarscaner.adapter.AdapterRiwayat.*
import com.anyarscaner.model.RiwayatModel

class AdapterRiwayat (var activity: Activity, var data:ArrayList<RiwayatModel>): RecyclerView.Adapter<Holder>() {

    class Holder(view: View):RecyclerView.ViewHolder(view) {
        val tvCreated_at = view.findViewById<TextView>(R.id.tv_createdat)
        val tvSn = view.findViewById<TextView>(R.id.tv_sn)
        val tvReward = view.findViewById<TextView>(R.id.tv_judul)
        val tvStatus = view.findViewById<TextView>(R.id.tv_status)
        val layout = view.findViewById<CardView>(R.id.layoutItem_riwayat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_riwayat,parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvCreated_at.text = data[position].created_at
        holder.tvSn.text = data[position].sn
        holder.tvReward.text = data[position].reward
        holder.tvStatus.text = data[position].status
    }

    override fun getItemCount(): Int {
        return data.size
    }
}