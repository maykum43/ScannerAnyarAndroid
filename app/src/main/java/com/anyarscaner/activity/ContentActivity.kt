package com.anyarscaner.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anyarscaner.R
import com.anyarscaner.databinding.ActivityDetailContentBinding

class ContentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDetail()
    }

    private fun getDetail() {
        val judul = intent.getStringExtra("judul")
        val img = intent.getIntExtra("img",0)
        val ket = intent.getStringExtra("ket")

        binding.tvDetaiContent.text = judul
        binding.ivDetailContent1.setImageResource(img)
        binding.ivDetailContent2.setImageResource(img)
        binding.tvKetContent.text = ket

        setSupportActionBar(findViewById(R.id.tollbar))
        supportActionBar!!.title = judul
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}