package com.anyarscaner.adapter

import android.app.Activity
import android.content.Intent
import android.icu.util.ULocale.getName
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.anyarscaner.R
import com.anyarscaner.activity.DetailHadiahActivity
import com.anyarscaner.activity.PoinMallActivity
import com.anyarscaner.model.HadiahModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.util.*

class AdapterVoucher (var activity: Activity, var data: ArrayList<HadiahModel>): RecyclerView.Adapter<AdapterVoucher.Holder>() {
    class Holder(view: View):RecyclerView.ViewHolder(view) {
        var ivHadiah = view.findViewById<ImageView>(R.id.iv_itemHadiah2)
        val tvTittle = view.findViewById<TextView>(R.id.tv_titleVoucher2)
        val tvPoin = view.findViewById<TextView>(R.id.tv_poinVoucher2)
//        val tvStok = view.findViewById<TextView>(R.id.tv_stok)
        val layout = view.findViewById<ImageView>(R.id.iv_bgVoucher2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVoucher.Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_voucher2 ,parent, false)
        return AdapterVoucher.Holder(view)
    }

    override fun onBindViewHolder(holder: AdapterVoucher.Holder, position: Int) {
//        holder.ivHadiah.setImageResource(data[position].foto)
        val image = "https://gpt.poin.cctvbandung.co.id/public/storage/Hadiah/FotoHadiah/" + data[position].foto
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.hadiah_default)
            .error(R.drawable.hadiah_default)
            .into(holder.ivHadiah)

        holder.tvTittle.text = data[position].name
        holder.tvPoin.text = data[position].req_poin
//        holder.tvStok.text = data[position].stok.toString()
        //format harga indo
    // NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(Integer.valueOf(data[position].req_poin))

        holder.ivHadiah.setOnClickListener {
            val activiti = Intent(activity, DetailHadiahActivity::class.java)
            activiti.putExtra("name", data[position].name)
            activiti.putExtra("req_poin", data[position].req_poin)
            activiti.putExtra("foto", data[position].foto)
//            activiti.putExtra("stok", data[position].stok.toString())
            activiti.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            activity.startActivity(activiti)
        }

        holder.tvTittle.setOnClickListener {
            val activiti = Intent(activity, DetailHadiahActivity::class.java)
            activiti.putExtra("name", data[position].name)
            activiti.putExtra("req_poin", data[position].req_poin)
            activiti.putExtra("foto", data[position].foto)
            activiti.putExtra("stok", data[position].stok.toString())
            activity.startActivity(activiti)
        }
        holder.tvPoin.setOnClickListener {
            val activiti = Intent(activity, DetailHadiahActivity::class.java)
            activiti.putExtra("name", data[position].name)
            activiti.putExtra("req_poin", data[position].req_poin)
            activiti.putExtra("foto", data[position].foto)
            activiti.putExtra("stok", data[position].stok.toString())
            activity.startActivity(activiti)
        }
//        holder.tvStok.setOnClickListener {
//            val activiti = Intent(activity, DetailHadiahActivity::class.java)
//            activiti.putExtra("name", data[position].name)
//            activiti.putExtra("req_poin", data[position].req_poin)
//            activiti.putExtra("foto", data[position].foto)
//            activiti.putExtra("stok", data[position].stok.toString())
//            activity.startActivity(activiti)
//        }
        holder.layout.setOnClickListener {
            val activiti = Intent(activity, DetailHadiahActivity::class.java)
            activiti.putExtra("name", data[position].name)
            activiti.putExtra("req_poin", data[position].req_poin)
            activiti.putExtra("foto", data[position].foto)
            activiti.putExtra("stok", data[position].stok.toString())
            activity.startActivity(activiti)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
}