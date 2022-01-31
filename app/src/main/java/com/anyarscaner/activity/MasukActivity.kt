package com.anyarscaner.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.anyarscaner.R
import com.anyarscaner.helper.SharedPref

class MasukActivity : AppCompatActivity() {
    lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masuk)

        s = SharedPref(this)

        mainButton()
    }

    fun mainButton(){

        //Inisialisasi ID (Button)
        val btnLogin = findViewById<Button>(R.id.btn_ProsesLogin)
        val btnRegist = findViewById<TextView>(R.id.tv_regist)

        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btnRegist.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}