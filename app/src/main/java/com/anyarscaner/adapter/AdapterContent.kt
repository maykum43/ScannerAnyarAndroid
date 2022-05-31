package com.anyarscaner.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anyarscaner.R
import com.anyarscaner.activity.ContentActivity
import com.anyarscaner.activity.DetailHadiahActivity
import com.anyarscaner.model.ContentData

class AdapterContent(var activity: Activity,private val contentList: List<ContentData>):RecyclerView.Adapter<AdapterContent.ContentViewHolder>() {
    inner class  ContentViewHolder(v:View):RecyclerView.ViewHolder(v){
        var judulContent = v.findViewById<TextView>(R.id.tv_content)
        var imgCOntent = v.findViewById<ImageView>(R.id.iv_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val infalter = LayoutInflater.from(parent.context)
        val v = infalter.inflate(R.layout.rv_content_item,parent,false)
        return ContentViewHolder(v)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val contentLists = contentList[position]
        holder.judulContent.text = contentLists.judulContent
        holder.imgCOntent.setImageResource(contentLists.imgContent)

        holder.imgCOntent.setOnClickListener{
            val activiti = Intent(activity, ContentActivity::class.java)
            activiti.putExtra("judul", contentList[position].judulContent)
            activiti.putExtra("img", contentList[position].imgContent)
            activiti.putExtra("ket", contentList[position].ketContent)
            activiti.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            activity.startActivity(activiti)
        }
    }

    override fun getItemCount(): Int {
        return contentList.size
    }
}