package com.anyarscaner.adapter

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.anyarscaner.R
import com.anyarscaner.model.RiwRedModel

class AdapterRiwRed(var activity: Activity, var data: ArrayList<RiwRedModel>): RecyclerView.Adapter<AdapterRiwRed.Holder>() {
    class Holder(view: View):RecyclerView.ViewHolder(view) {
        val tvCreateAtRiwred = view.findViewById<TextView>(R.id.tv_createdat_riwred)
        val tvNamaHadiah = view.findViewById<TextView>(R.id.tv_nama_hadiah)
        val tvStatus = view.findViewById<TextView>(R.id.tv_status)
        val tvJmlPoin = view.findViewById<TextView>(R.id.tv_jml_poin)
        val layout = view.findViewById<CardView>(R.id.layoutItem_riwred)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterRiwRed.Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_riwred ,parent, false)
        return AdapterRiwRed.Holder(view)
    }

    override fun onBindViewHolder(holder: AdapterRiwRed.Holder, position: Int) {
        holder.tvCreateAtRiwred.text = data[position].created_at
        holder.tvNamaHadiah.text = data[position].nama_hadiah
        holder.tvStatus.text = data[position].status
        holder.tvJmlPoin.text = data[position].jml_poin.toString()

        if (holder.tvStatus.text == "Diproses"){
            holder.tvStatus.setTextColor(Color.parseColor("#1A60BC"))
        }else if (holder.tvStatus.text == "Selesai"){
            holder.tvStatus.setTextColor(Color.parseColor("#00FF08"))
        }else if(holder.tvStatus.text == "Belum Selesai"){
            holder.tvStatus.setTextColor(Color.parseColor("#FF0000"))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}