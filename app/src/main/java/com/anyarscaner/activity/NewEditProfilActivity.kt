package com.anyarscaner.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anyarscaner.MainActivity
import com.anyarscaner.R
import com.anyarscaner.api.ApiConfig
import com.anyarscaner.helper.SharedPref
import com.anyarscaner.model.ResponModel
import com.anyarscaner.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewEditProfilActivity : AppCompatActivity() {

    lateinit var s: SharedPref
    lateinit var user :User
    private lateinit var name1 :TextView
    private lateinit var email1: TextView

    private lateinit var name: TextView
    private lateinit var email: TextView
    private lateinit var phone: TextView
    private lateinit var norek: TextView
    private lateinit var nama_bank: TextView
    private lateinit var atas_nama: TextView
    private lateinit var akun_ol: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofil)

        s = SharedPref(this)

        setData()

        val btn_simpan = findViewById<Button>(R.id.btn_save)

        btn_simpan.setOnClickListener {
            simpanData()
        }
    }

    private fun setData() {
        name1 = findViewById<EditText>(R.id.tv_namaUser1)
        email1 = findViewById<EditText>(R.id.tv_emailUser1)

        name = findViewById<EditText>(R.id.tv_namaUser)
        phone = findViewById<EditText>(R.id.tv_notlp)
        email = findViewById<EditText>(R.id.tv_email)
        norek = findViewById<EditText>(R.id.tv_norek)
        nama_bank = findViewById<EditText>(R.id.tv_nama_bank)
        atas_nama = findViewById<EditText>(R.id.tv_atas_nama)
        akun_ol = findViewById<EditText>(R.id.tv_nama_akun_ol)

        name1.text = s.getUser()?.name
        email1.text = s.getUser()?.email

//        val id = user.getId()
//instanceRetrofit
//        ApiConfig.instanceRetrofit.cari_pelangan(id).enqueue(object :
//            Callback<ResponModel> {
//            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
//                val respon = response.body()!!
//                if(respon.success == 1){
//                    //berhasil
////                      s.setUser(respon.user)
//                    s.setString(s.nama, respon.user.name)
//                    s.setString(s.email, respon.user.email)
//                    s.setString(s.phone, respon.user.phone)
//                    s.setString(s.norek, respon.user.norek)
//                    s.setString(s.nama_bank, respon.user.nama_bank)
//                    s.setString(s.atas_nama, respon.user.atas_nama)
//                    s.setString(s.akun_ol, respon.user.nama_akun_ol)
//
//                    val intent = Intent(this@NewEditProfilActivity, MainActivity::class.java)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//                    startActivity(intent)
//                    finish()
//                }else {
//                    //gagal
//                    Toast.makeText(this@NewEditProfilActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
//                Toast.makeText(this@NewEditProfilActivity, "Error: "+t.message, Toast.LENGTH_SHORT).show()
//            }
//
//        })
    }

    private fun simpanData() {

    }
}