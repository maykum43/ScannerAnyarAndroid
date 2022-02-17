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
import com.anyarscaner.helper.SharedPref
import com.anyarscaner.model.RiwayatModel
import com.anyarscaner.model.SnModel

class AdapterCariSn(var activity: Activity, var data: ArrayList<SnModel>): RecyclerView.Adapter<AdapterCariSn.Holder>() {

    lateinit var s: SharedPref

//    lateinit var tv_user: String

//    lateinit var tv_sn :TextView
//    lateinit var tv_user :TextView

    class Holder(view: View):RecyclerView.ViewHolder(view) {
        val tvSN = view.findViewById<TextView>(R.id.tv_sn)
        val layout = view.findViewById<CardView>(R.id.layoutItem_sn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_sn,parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvSN.text = data[position].sn

        holder.layout.setOnClickListener{

            val activiti = Intent(activity, CreateHisActivity::class.java)
            activiti.putExtra("sn",data[position].sn)
            activity.startActivity(activiti)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}