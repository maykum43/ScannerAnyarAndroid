package com.anyarscaner.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.anyarscaner.R
import com.anyarscaner.activity.CreateHisActivity
import com.anyarscaner.model.RiwayatModel

class AdapterHis(var activity: Activity, var data: ArrayList<RiwayatModel>): RecyclerView.Adapter<AdapterHis.Holder>() {

    class Holder(view: View):RecyclerView.ViewHolder(view) {
        val tvCreateAt = view.findViewById<TextView>(R.id.tv_createdat)
        val tvSN = view.findViewById<TextView>(R.id.tv_sn)
        val tvJudul = view.findViewById<TextView>(R.id.tv_judul)
        val tvStatus = view.findViewById<TextView>(R.id.tv_status)
        val layout = view.findViewById<CardView>(R.id.layoutItem_riwayat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHis.Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_riwayat ,parent, false)
        return AdapterHis.Holder(view)
    }

    override fun onBindViewHolder(holder: AdapterHis.Holder, position: Int) {
        holder.tvCreateAt.text = data[position].created_at
        holder.tvSN.text = data[position].sn
        holder.tvJudul.text = data[position].judul
        holder.tvStatus.text = data[position].status


//        holder.layout.setOnClickListener{
//
//            val activiti = Intent(activity, CreateHisActivity::class.java)
//            activiti.putExtra("sn",data[position].sn)
//            activity.startActivity(activiti)
//        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}