package com.anyarscaner.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anyarscaner.R
import com.anyarscaner.api.ApiConfig
import com.anyarscaner.helper.SharedPref
import com.anyarscaner.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

        lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        s = SharedPref(this)

        val btnDaftar = findViewById<Button>(R.id.btn_daftar)

        btnDaftar.setOnClickListener{
            register()
        }

        //set Tollbar
        setSupportActionBar(findViewById(R.id.tollbar))
        supportActionBar!!.title = "Registrasi"
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun register(){
        val edt_nama = findViewById<EditText>(R.id.tv_namaUser)
        val edt_notlp = findViewById<EditText>(R.id.tv_notlp)
        val edt_email = findViewById<EditText>(R.id.tv_email)
        val edt_norek = findViewById<EditText>(R.id.tv_norek)
        val edt_bank = findViewById<EditText>(R.id.tv_nama_bank)
        val edt_atas_nama = findViewById<EditText>(R.id.tv_atas_nama)
        val edt_akun_ol = findViewById<EditText>(R.id.tv_nama_akun_ol)
        val edt_pass = findViewById<EditText>(R.id.tv_password)
        val edt_alamat = findViewById<EditText>(R.id.tv_alamat)


        if(edt_nama.text.isEmpty()){
            edt_nama.error = "Kolom nama tidak boleh kosong!"
            edt_nama.requestFocus()
            return
        } else if(edt_email.text.isEmpty()){
            edt_email.error = "Kolom email tidak boleh kosong!"
            edt_email.requestFocus()
            return
        } else if(edt_pass.text.isEmpty()){
            edt_pass.error = "Kolom password tidak boleh kosong!"
            edt_pass.requestFocus()
            return
        }else if(edt_alamat.text.isEmpty()){
            edt_alamat.error = "Kolom Alamat tidak boleh kosong!"
            edt_alamat.requestFocus()
            return
        }else if(edt_notlp.text.isEmpty()){
            edt_notlp.error = "Kolom No. Telepon tidak boleh kosong!"
            edt_notlp.requestFocus()
            return
        } else if(edt_norek.text.isEmpty()){
            edt_norek.error = "Kolom Nomor Rekening tidak boleh kosong!"
            edt_norek.requestFocus()
            return
        } else if(edt_bank.text.isEmpty()){
            edt_bank.error = "Kolom Nama Bank tidak boleh kosong!"
            edt_bank.requestFocus()
            return
        } else if(edt_atas_nama.text.isEmpty()){
            edt_atas_nama.error = "Kolom nama pemilik rekening tidak boleh kosong!"
            edt_atas_nama.requestFocus()
            return
        } else if(edt_akun_ol.text.isEmpty()) {
            edt_akun_ol.error = "Kolom Username Akun Online Shop tidak boleh kosong!"
            edt_akun_ol.requestFocus()
            return
        }


        val pb = findViewById<ProgressBar>(R.id.pb_regist)

        pb.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.register(
            edt_nama.text.toString(),
            edt_email.text.toString(),
            edt_pass.text.toString(),
            edt_alamat.text.toString(),
            edt_notlp.text.toString(),
            edt_norek.text.toString(),
            edt_bank.text.toString(),
            edt_atas_nama.text.toString(),
            edt_akun_ol.text.toString()).enqueue(object : Callback<ResponModel>{
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                pb.visibility = View.GONE

                val respon = response.body()!!

                if(respon.success == 1){
                    //berhasil
                    s.setStatusLogin(true)
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@RegisterActivity, "Success: "+respon.message +" Selamat Datang "+respon.user.name, Toast.LENGTH_LONG).show()
                }else {
                    //gagal
                    Toast.makeText(this@RegisterActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText(this@RegisterActivity, "Error: "+t.message, Toast.LENGTH_SHORT).show()
            }
//
        })
    }

    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
        return super.onSupportNavigateUp()
    }
}