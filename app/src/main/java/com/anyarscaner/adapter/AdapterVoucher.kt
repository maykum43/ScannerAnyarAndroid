package com.anyarscaner.adapter

import android.app.Activity
import android.icu.number.IntegerWidth
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.anyarscaner.R
import com.anyarscaner.model.HadiahModel
import com.anyarscaner.model.RiwayatModel
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class AdapterVoucher (var activity: Activity, var data: ArrayList<HadiahModel>): RecyclerView.Adapter<AdapterVoucher.Holder>() {
    class Holder(view: View):RecyclerView.ViewHolder(view) {
        var ivHadiah = view.findViewById<ImageView>(R.id.iv_itemHadiah)
        val tvTittle = view.findViewById<TextView>(R.id.tv_titleVoucher)
        val tvPoin = view.findViewById<TextView>(R.id.tv_poinVoucher)
        val layout = view.findViewById<ConstraintLayout>(R.id.layoutItem_voucher)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVoucher.Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_voucher2 ,parent, false)
        return AdapterVoucher.Holder(view)
    }

    override fun onBindViewHolder(holder: AdapterVoucher.Holder, position: Int) {
//        holder.ivHadiah.setImageResource(data[position].foto)
        val image = "http://192.168.1.235/WebAdminScanner2/public/storage/Hadiah/FotoHadiah/" + data[position].foto
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.hadiah_default)
            .error(R.drawable.hadiah_default)
            .into(holder.ivHadiah)

        holder.tvTittle.text = data[position].name
        holder.tvPoin.text = data[position].req_poin
        //format harga indo
    // NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(Integer.valueOf(data[position].req_poin))
    }

    override fun getItemCount(): Int {
        return data.size
    }
}