package com.anyarscaner.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.anyarscaner.R

class SupportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support)

        klikBantuan()
        klikGmail()

        //set Tollbar
        setSupportActionBar(findViewById(R.id.tollbar))
        supportActionBar!!.title = "Helpdesk"
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun klikGmail() {
        val btnGmail = findViewById<ImageButton>(R.id.btn_gmail_admin)

        btnGmail.setOnClickListener {
            val email = "admin@cctvbandung.co.id"
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
    }

    private fun klikBantuan() {
        val btnBantuan = findViewById<RelativeLayout>(R.id.rl_bantuan)

        btnBantuan.setOnClickListener {
            val inData = Intent(this@SupportActivity, BantuanActivity::class.java)
            startActivity(inData)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}