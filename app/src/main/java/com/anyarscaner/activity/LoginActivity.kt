package com.anyarscaner.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
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

    private lateinit var email: EditText
    private lateinit var password:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        s = SharedPref(this)
        // getting the data which is stored in shared preferences.

        // getting the data which is stored in shared preferences.


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
        email = findViewById<EditText>(R.id.tv_email)
        password = findViewById<EditText>(R.id.tv_password)

        if (email.text.isEmpty()) {
            email.error = "Kolom email tidak boleh kosong!"
            email.requestFocus()
            return
        } else if (password.text.isEmpty()) {
            password.error = "Kolom password tidak boleh kosong!"
            password.requestFocus()
            return
        }
        val pb = findViewById<ProgressBar>(R.id.pb_login)

        pb.visibility = View.VISIBLE


        ApiConfig.instanceRetrofit.login(email.text.toString(),password.text.toString()).enqueue(object : Callback<ResponModel>{
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                pb.visibility = View.GONE

                val respon = response.body()!!
                if(respon.success == 1){
                    //berhasil
                    s.setStatusLogin(true)
//                      s.setUser(respon.user)
                    s.setInt(s.id, respon.user.id)
                    s.setString(s.nama, respon.user.name)
                    s.setString(s.email, respon.user.email)
                    s.setString(s.phone, respon.user.phone)
                    s.setString(s.norek, respon.user.norek)
                    s.setString(s.nama_bank, respon.user.nama_bank)
                    s.setString(s.atas_nama, respon.user.atas_nama)
                    s.setString(s.akun_ol, respon.user.nama_akun_ol)

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@LoginActivity, "Success: "+respon.message, Toast.LENGTH_LONG).show()
                }else {
                    //gagal
                    Toast.makeText(this@LoginActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Terjadi Kesalahan: "+t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}