package com.anyarscaner.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.anyarscaner.R
import com.anyarscaner.model.HadiahModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class DetailHadiahActivity : AppCompatActivity() {

    lateinit var tv_title: TextView
    lateinit var tv_poin: TextView
    lateinit var tv_stok: TextView
    lateinit var iv_hadiah: ImageView
    lateinit var toolbar : Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hadiah)

        tv_title = findViewById(R.id.tv_tittle)
        tv_poin = findViewById(R.id.tv_poin)
        iv_hadiah = findViewById(R.id.image)
        tv_stok = findViewById(R.id.tv_stokHadiah)
//        toolbar= findViewById(R.id.tollbar)

        getInfo()
    }

    fun getInfo(){
//        val data = intent.getStringExtra("extra")
//        val hadiah = Gson().fromJson<HadiahModel>(data, HadiahModel::class.java)

        val name = intent.getStringExtra("name")
        val req_poin = intent.getStringExtra("req_poin")
        val foto = intent.getStringExtra("foto")
        val stok = intent.getStringExtra("stok")

        //set value
        tv_title.text = name
        tv_poin.text = req_poin
        tv_stok.text = stok

        val img = "http://192.168.1.235/WebAdminScanner2/public/storage/Hadiah/FotoHadiah/" + foto
        Picasso.get()
            .load(img)
            .placeholder(R.drawable.hadiah_default)
            .error(R.drawable.hadiah_default)
            .into(iv_hadiah)
//
//        //set Tollbar
        setSupportActionBar(findViewById(R.id.tollbar))
        supportActionBar!!.title = name
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}