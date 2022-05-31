package com.anyarscaner.adapter

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.anyarscaner.R
import com.anyarscaner.activity.ContentActivity
import com.anyarscaner.activity.DetailItemReedemActivity
import com.anyarscaner.model.RiwayatRedeemModel

class AdapterRiwRed(var activity: Activity, var data: ArrayList<RiwayatRedeemModel>):
    RecyclerView.Adapter<AdapterRiwRed.Holder>() {


    class Holder(view: View):RecyclerView.ViewHolder(view) {
        val creted_at = view.findViewById<TextView>(R.id.tv_createdat_redeem)
        val nama_hadiah = view.findViewById<TextView>(R.id.tv_nama_hadiah)
        val status = view.findViewById<TextView>(R.id.tv_status)
        val jml_poin = view.findViewById<TextView>(R.id.tv_poinHadiah)
        val layoutRed = view.findViewById<CardView>(R.id.layoutItem_riwayatRedeem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterRiwRed.Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_riwayat_redeem ,parent, false)
        return AdapterRiwRed.Holder(view)
    }

    override fun onBindViewHolder(holder: AdapterRiwRed.Holder, position: Int) {
        holder.creted_at.text = data[position].created_at
        holder.nama_hadiah.text = data[position].nama_hadiah
        holder.status.text = data[position].status
        holder.jml_poin.text = data[position].jml_poin

        holder.layoutRed.setOnClickListener {
            val activiti = Intent(activity, DetailItemReedemActivity::class.java)
            activiti.putExtra("nama_hadiah", data[position].nama_hadiah)
            activiti.putExtra("created_at", data[position].created_at)
            activiti.putExtra("jml_poin", data[position].jml_poin)
            activiti.putExtra("status", data[position].status)
            activiti.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            activity.startActivity(activiti)
        }

        if (holder.status.text == "Diproses"){
            holder.status.setTextColor(Color.parseColor("#1A60BC"))
        }else if(holder.status.text == "Selesai"){
            holder.status.setTextColor(Color.parseColor("#00FF08"))
        }else if(holder.status.text == "Dibatalkan"){
            holder.status.setTextColor(Color.parseColor("#FF0000"))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}