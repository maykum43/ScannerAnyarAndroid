package com.anyarscaner.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.anyarscaner.MainActivity
import com.anyarscaner.R
import com.anyarscaner.helper.SharedPref

class ScannerActivity : AppCompatActivity()  {
    lateinit var s: SharedPref
    lateinit var txt_sn: TextView
//    public class ViewActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        s = SharedPref(this)


        mainButton()


    }

    fun mainButton(){
        val btn_scan = findViewById<Button>(R.id.btn_scan_sn)
        val btn_kembali = findViewById<ImageView>(R.id.btn_kembali)

        btn_kembali.setOnClickListener {
            val intent = Intent(this@ScannerActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
//        val btn_cari_sn = findViewById<Button>(R.id.)
    }

    fun setData(){
        txt_sn.text = s.getString(s.sn)
    }
}