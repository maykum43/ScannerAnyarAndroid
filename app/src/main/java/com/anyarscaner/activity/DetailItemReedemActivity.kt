package com.anyarscaner.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.anyarscaner.R
import com.anyarscaner.databinding.ActivityDetailItemRedeemBinding
import com.anyarscaner.helper.SharedPref

class DetailItemReedemActivity: AppCompatActivity() {
    private lateinit var binding : ActivityDetailItemRedeemBinding

    lateinit var s : SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailItemRedeemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        s = SharedPref(this)
        header()
        setData()

        //set Tollbar
        setSupportActionBar(findViewById(R.id.tollbar))
        supportActionBar!!.title = "Detail Redeem Hadiah"
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        layout(binding.root)
    }

    private fun setData() {
//        Data Hadiah
        val tv_namaHadiah = binding.tvNamaHadiahDt
        val tv_poin = binding.tvPoinHadiahDt
        val tv_create_at = binding.tvCreatedatDt
        val tv_namaUser = binding.tvNamaUserDt
        val tv_emailUser = binding.tvEmailDt
        val tv_noTlp = binding.tvNotlpDt
        val tv_alamat = binding.tvAlamatDt

        tv_namaHadiah.text = intent.getStringExtra("nama_hadiah")
        tv_poin.text = intent.getStringExtra("jml_poin")
        tv_create_at.text = intent.getStringExtra("created_at")
        tv_namaUser.setText(s.getString(s.name))
        tv_emailUser.setText(s.getString(s.email))
        tv_noTlp.setText(s.getString(s.phone))
        tv_alamat.setText(s.getString(s.alamat))
    }

    private fun header() {
        val nm_user = findViewById<TextView>(R.id.txt_nama)
        val email_user = findViewById<TextView>(R.id.txt_email)
        val jml_hadiah = findViewById<TextView>(R.id.tv_totalPoin)
        val jdl_poin = findViewById<TextView>(R.id.tv_jdl_poin)

        nm_user.setText(s.getString(s.name))
        email_user.setText(s.getString(s.email))
        jml_hadiah.setText(s.getString(s.jumlah))
        jdl_poin.setText("Hadiah")

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}