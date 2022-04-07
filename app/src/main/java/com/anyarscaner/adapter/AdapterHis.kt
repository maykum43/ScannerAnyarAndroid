package com.anyarscaner.adapter

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.anyarscaner.R
import com.anyarscaner.activity.CreateHisActivity
import com.anyarscaner.model.HadiahModel
import com.anyarscaner.model.RiwayatModel

class AdapterHis(var activity: Activity, var data: ArrayList<RiwayatModel>): RecyclerView.Adapter<AdapterHis.Holder>() {

    class Holder(view: View):RecyclerView.ViewHolder(view) {
        val tvCreateAt = view.findViewById<TextView>(R.id.tv_createdat)
        val tvSN = view.findViewById<TextView>(R.id.tv_sn)
        val tvModel = view.findViewById<TextView>(R.id.tv_model)
        val tvPoin = view.findViewById<TextView>(R.id.tv_poin)
        val layout = view.findViewById<CardView>(R.id.layoutItem_riwayat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHis.Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_riwayat ,parent, false)
        return AdapterHis.Holder(view)
    }

    override fun onBindViewHolder(holder: AdapterHis.Holder, position: Int) {
        holder.tvCreateAt.text = data[position].created_at
        holder.tvSN.text = data[position].sn
        holder.tvModel.text = data[position].model
        holder.tvPoin.text = data[position].poin

//        if (holder.tvStatus.text == "Diproses"){
//            holder.tvStatus.setTextColor(Color.parseColor("#1A60BC"))
//        }else if (holder.tvStatus.text == "Selesai"){
//            holder.tvStatus.setTextColor(Color.parseColor("#00FF08"))
//        }else if(holder.tvStatus.text == "Belum Selesai"){
//            holder.tvStatus.setTextColor(Color.parseColor("#FF0000"))
//        }


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