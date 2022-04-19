package com.anyarscaner.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.anyarscaner.MainActivity
import com.anyarscaner.R

class AboutActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_aboutus)

        //set Tollbar
        setSupportActionBar(findViewById(R.id.tollbar))
        supportActionBar!!.title = "Info Perusahaan"
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun openGmail(view: View?){
        val email = "support01@cctvbandung.co.id"
        val subject = "Bantuan Aplikasi Voucher Cashback"
        val pesan = "Halo support GPT, saya user aplikasi Voucher Cashback GPT, boleh minta bantuanya.(boleh dijelasin keluhanya kenapa)"

        val recipients = email.split(",".toRegex()).toTypedArray()

        val i = Intent(Intent.ACTION_SEND)
        i.putExtra(Intent.EXTRA_EMAIL, recipients)
        i.putExtra(Intent.EXTRA_SUBJECT, subject)
        i.putExtra(Intent.EXTRA_TEXT, pesan)

        i.type = "message/rfc822"
        this.startActivity(Intent.createChooser(i, "Pilih aplikasi Email"))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}