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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfilActivity : AppCompatActivity() {

    lateinit var s: SharedPref

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

        name = findViewById<EditText>(R.id.tv_namaUser)
        phone = findViewById<EditText>(R.id.tv_notlp)
        email = findViewById<EditText>(R.id.tv_email)
        norek = findViewById<EditText>(R.id.tv_norek)
        nama_bank = findViewById<EditText>(R.id.tv_nama_bank)
        atas_nama = findViewById<EditText>(R.id.tv_atas_nama)
        akun_ol = findViewById<EditText>(R.id.tv_nama_akun_ol)

        setData()

        val btnSave = findViewById<Button>(R.id.btn_save)

        btnSave.setOnClickListener {
            simpanData()
        }

        //set Tollbar
        setSupportActionBar(findViewById(R.id.tollbar))
        supportActionBar!!.title = "Edit Data Pribadi"
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun setData(){
//        val user = s.getUser()!!

//        txt_nama.text = user.name
//        txt_email.text = user.email

        name.text = s.getString(s.name)
        email.text = s.getString(s.email)
        phone.text = s.getString(s.phone)
        norek.text = s.getString(s.norek)
        nama_bank.text = s.getString(s.bank)
        atas_nama.text = s.getString(s.ats_nama)
        akun_ol.text = s.getString(s.akun_ol)
    }

    fun simpanData() {

        if (name.text.isEmpty()) {
            name.error = "Kolom nama tidak boleh kosong!"
            name.requestFocus()
            return
        } else if (email.text.isEmpty()) {
            email.error = "Kolom email tidak boleh kosong!"
            email.requestFocus()
            return
        } else if (phone.text.isEmpty()) {
            phone.error = "Kolom No. Telepon tidak boleh kosong!"
            phone.requestFocus()
            return
        } else if (norek.text.isEmpty()) {
            norek.error = "Kolom Nomor Rekening tidak boleh kosong!"
            norek.requestFocus()
            return
        } else if (nama_bank.text.isEmpty()) {
            nama_bank.error = "Kolom Nama Bank tidak boleh kosong!"
            nama_bank.requestFocus()
            return
        } else if (atas_nama.text.isEmpty()) {
            atas_nama.error = "Kolom nama pemilik rekening tidak boleh kosong!"
            atas_nama.requestFocus()
            return
        } else if (akun_ol.text.isEmpty()) {
            akun_ol.error = "Kolom Username Akun Online Shop tidak boleh kosong!"
            akun_ol.requestFocus()
            return
        }

        val pb = findViewById<ProgressBar>(R.id.pb_edit)
//        var id = s.getInt(s.id)

        pb.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.edit_profil(
            email.text.toString(),
            name.text.toString(),
            phone.text.toString(),
            norek.text.toString(),
            nama_bank.text.toString(),
            atas_nama.text.toString(),
            akun_ol.text.toString()).enqueue(object :Callback<ResponModel>{
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                pb.visibility = View.GONE
                val respon = response.body()!!

                if(respon.success == 1){
                    //berhasil
                    s.setStatusLogin(true)
                    val intent = Intent(this@EditProfilActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@EditProfilActivity, "Success: "+respon.message +" Edit data"+respon.user.name, Toast.LENGTH_LONG).show()
                }else {
                    //gagal
                    Toast.makeText(this@EditProfilActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText(this@EditProfilActivity, "Error: "+t.message, Toast.LENGTH_SHORT).show()
            }

        })

//        ApiConfig.instanceRetrofit.edit_profil(
////            id,
//            email.text.toString(),
//            name.text.toString(),
//            phone.text.toString(),
//            norek.text.toString(),
//            nama_bank.text.toString(),
//            atas_nama.text.toString(),
//            akun_ol.text.toString(),
//        ).enqueue(object : Callback<ResponModel>{
//            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
//                pb.visibility = View.GONE
//
//                val respon = response.body()!!
//
//                if(respon.success == 1){
//                    //berhasil
//                    s.setStatusLogin(true)
//                    val intent = Intent(this@EditProfilActivity, MainActivity::class.java)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//                    startActivity(intent)
//                    finish()
//                    Toast.makeText(this@EditProfilActivity, "Success: "+respon.message +" Edit data"+respon.user.name, Toast.LENGTH_LONG).show()
//                }else {
//                    //gagal
//                    Toast.makeText(this@EditProfilActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
//                pb.visibility = View.GONE
//                Toast.makeText(this@EditProfilActivity, "Error: "+t.message, Toast.LENGTH_SHORT).show()
//            }
//
//        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
