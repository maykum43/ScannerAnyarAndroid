package com.anyarscaner.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.anyarscaner.MainActivity
import com.anyarscaner.R

class BantuanActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bantuan)

        var btnBack : ImageView
//        var btnWA : ImageView
        var semuaPesan : String
        var noHp : String


        btnBack = findViewById(R.id.img_back)
//        btnWA = findViewById(R.id.btn_wa)

        btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

//        btnWA.setOnClickListener{
//                openWA(this)
//        }

    }

//    fun openWA(view: BantuanActivity) {
//        val number = "6285880827382"
//        val url = "https://api.whatsapp.com/send?phone=$number"
//        val i = Intent(Intent.ACTION_VIEW)
//        i.setPackage("com.whatsapp")
//        i.data = Uri.parse(url)
//        startActivity(i)
//    }

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
}