package com.anyarscaner.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anyarscaner.MainActivity
import com.anyarscaner.R
import com.anyarscaner.app.ApiConfig
import com.anyarscaner.helper.SharedPref
import com.anyarscaner.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        s = SharedPref(this)

        //Inisialisasi ID (Button)
        val btnLogin2 = findViewById<TextView>(R.id.btn_login)
        val btnDaftar = findViewById<TextView>(R.id.btn_daftar)

        btnLogin2.setOnClickListener {
            login()
        }

        btnDaftar.setOnClickListener {
            regist()
        }
    }

    fun regist() {
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    fun login() {
        val edt_email = findViewById<EditText>(R.id.tv_email)
        val edt_pass = findViewById<EditText>(R.id.tv_password)

        if (edt_email.text.isEmpty()) {
            edt_email.error = "Kolom email tidak boleh kosong!"
            edt_email.requestFocus()
            return
        } else if (edt_pass.text.isEmpty()) {
            edt_pass.error = "Kolom password tidak boleh kosong!"
            edt_pass.requestFocus()
            return
        }

        val pb = findViewById<ProgressBar>(R.id.pb_login)

        pb.visibility = View.VISIBLE

        ApiConfig.instanceRetrofit.login(edt_email.text.toString(),edt_pass.text.toString()).enqueue(object :
            Callback<ResponModel> {

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                //Response Gagal
                pb.visibility = View.GONE
                Toast.makeText(this@LoginActivity, "Error: "+t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                pb.visibility = View.GONE

                val respon = response.body()!!

                if(respon.success == 1){
                    //berhasil
                    s.setStatusLogin(true)
//                      s.setUser(respon.user)
                    s.setString(s.nama, respon.user.name)
                    s.setString(s.email, respon.user.email)

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@LoginActivity, "Success: "+respon.message +" Selamat Datang "+respon.user.name, Toast.LENGTH_LONG).show()
                }else {
                    //gagal
                    Toast.makeText(this@LoginActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}