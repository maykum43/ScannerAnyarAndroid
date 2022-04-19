package com.anyarscaner.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.anyarscaner.MainActivity
import com.anyarscaner.R

class BantuanActivity : AppCompatActivity() {

    lateinit var hidenLayoutSN : LinearLayout
    lateinit var hidenLayoutSK : LinearLayout
    lateinit var cardSN : CardView
    lateinit var arr_upSN : ImageView
    lateinit var arr_downSN : ImageView
    lateinit var cardSK : CardView
    lateinit var arr_upSK : ImageView
    lateinit var arr_downSK : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bantuan)

        arr_upSN = findViewById(R.id.arrow_up1)
        arr_downSN =findViewById(R.id.arrow_down1)
        hidenLayoutSN = findViewById(R.id.item_info1)
        cardSN = findViewById(R.id.cardItemInfo)

        arr_upSK = findViewById(R.id.arrow_up2)
        arr_downSK =findViewById(R.id.arrow_down2)
        hidenLayoutSK = findViewById(R.id.item_info2)
        cardSK = findViewById(R.id.cardItemInfoSnK)

        cardSN.setOnClickListener {
            expandSN()
        }
        cardSK.setOnClickListener {
            expandSK()
        }


        arr_downSN.setOnClickListener {
            expandSN()
        }
        arr_downSK.setOnClickListener {
            expandSK()
        }

        //set Tollbar
        setSupportActionBar(findViewById(R.id.tollbar))
        supportActionBar!!.title = "Info & Bantuan Aplikasi"
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    private fun expandSK() {
        if (hidenLayoutSK.visibility == View.VISIBLE){
            TransitionManager.beginDelayedTransition(cardSK)
            hidenLayoutSK.visibility = View.GONE
            arr_downSK.visibility = View.VISIBLE
            arr_upSK.visibility = View.GONE
        }else{
            TransitionManager.beginDelayedTransition(cardSK)
            hidenLayoutSK.visibility = View.VISIBLE
            arr_downSK.visibility = View.GONE
            arr_upSK.visibility = View.VISIBLE
        }
    }

    private fun expandSN() {
        if (hidenLayoutSN.visibility == View.VISIBLE){
            TransitionManager.beginDelayedTransition(cardSN)
            hidenLayoutSN.visibility = View.GONE
            arr_downSN.visibility = View.VISIBLE
            arr_upSN.visibility = View.GONE
        }else{
            TransitionManager.beginDelayedTransition(cardSN)
            hidenLayoutSN.visibility = View.VISIBLE
            arr_downSN.visibility = View.GONE
            arr_upSN.visibility = View.VISIBLE
        }
    }

    fun openEmail(view: View?) {
        val intent = packageManager.getLaunchIntentForPackage("com.google.android.gm")
        intent?.let { startActivity(it) }
    }

    fun openWa(view: View?){
        val number = "628112290908"
        val pesan = "Halo support GPT, saya pengguna aplikasi Voucer Cashback GPT. Boleh minta Bantuanya ?"
        val url = "https://api.whatsapp.com/send?phone=$number&text=$pesan"
        val i = Intent(Intent.ACTION_VIEW)
        i.setPackage("com.whatsapp")
        i.data = Uri.parse(url)
        startActivity(i)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}