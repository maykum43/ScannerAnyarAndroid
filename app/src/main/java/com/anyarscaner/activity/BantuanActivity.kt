package com.anyarscaner.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.anyarscaner.MainActivity
import com.anyarscaner.R

class BantuanActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bantuan)

        var btnBack : ImageView

        btnBack = findViewById(R.id.img_back)

        btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}