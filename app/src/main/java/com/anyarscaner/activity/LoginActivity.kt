package com.anyarscaner.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.anyarscaner.MainActivity
import com.anyarscaner.R
import com.anyarscaner.api.ApiConfig
import com.anyarscaner.helper.SharedPref
import com.anyarscaner.model.ResponModel
import com.anyarscaner.model.User
import com.anyarscaner.responseModel.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity: AppCompatActivity() {
    //    private lateinit var binding: ActivityLoginBinding

    lateinit var s: SharedPref

    private lateinit var email: EditText
    private lateinit var password:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        s = SharedPref(this)
        //Inisialisasi ID (Button)
        val btnLogin2 = findViewById<TextView>(R.id.btn_login)
        val btnDaftar = findViewById<TextView>(R.id.btn_daftar)

        btnLogin2.setOnClickListener {
//            s.setStatusLogin(true)
            login()
        }

        btnDaftar.setOnClickListener {
            regist()
        }
    }

    private fun regist() {
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun login() {
        email = findViewById(R.id.tv_email)
        password = findViewById(R.id.tv_password)

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

        ApiConfig.instanceRetrofit.login(email.text.toString(),password.text.toString())
            .enqueue(object : Callback<ResponModel>{
                override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                    pb.visibility = View.GONE
                    val respon = response.body()!!
                    if(respon.success == 1){
                        s.setStatusLogin(true)
//                        s.setUser(respon.user)
//                        Data User
                        s.setString(s.name, respon.user.name.toString())
                        s.setString(s.email, respon.user.email.toString())
                        s.setString(s.phone, respon.user.phone.toString())
                        s.setString(s.alamat, respon.user.alamat.toString())
                        s.setString(s.norek, respon.user.norek.toString())
                        s.setString(s.bank, respon.user.nama_bank.toString())
                        s.setString(s.ats_nama, respon.user.atas_nama.toString())
                        s.setString(s.akun_ol, respon.user.nama_akun_ol.toString())

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
                    Toast.makeText(applicationContext,t.localizedMessage, Toast.LENGTH_SHORT).show()
                }


            })

    }
}