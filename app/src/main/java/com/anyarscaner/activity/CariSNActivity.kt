package com.anyarscaner.activity

import android.content.Intent
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

class CariSNActivity : AppCompatActivity() {

//    lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carisn)

//        s = SharedPref(this)

        val btn_back = findViewById<ImageView>(R.id.btn_back)
        val btn_carisn = findViewById<Button>(R.id.btn_cariSN)


        btn_back.setOnClickListener{
            // fungsi kembali ke fragment
        }

        btn_carisn.setOnClickListener{
            //fungsi cari sn
//            cariSN()
        }

//        s = SharedPref(this)

//        mainButton()
    }

//    fun cariSN(){
//        val edt_carisn = findViewById<EditText>(R.id.tv_sn)
//
//        if(edt_carisn.text.isEmpty()){
//            edt_carisn.error = "Kolom nama tidak boleh kosong!"
//            edt_carisn.requestFocus()
//            return
//        }
//
//        val pb = findViewById<ProgressBar>(R.id.pb_regist)
//
//        pb.visibility = View.VISIBLE
//        ApiConfig.instanceRetrofit.cari_sn(edt_nama.text.toString(),
//            edt_email.text.toString(),
//            edt_notlp.text.toString(),
//            edt_norek.text.toString(),
//            edt_bank.text.toString(),
//            edt_atas_nama.text.toString(),
//            edt_akun_ol.text.toString(),
//            edt_pass.text.toString(),
//        ).enqueue(object :
//            Callback<ResponModel> {
//
//            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
//                //Response Gagal
//                pb.visibility = View.GONE
//                Toast.makeText(this@RegisterActivity, "Error: "+t.message, Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
//                pb.visibility = View.GONE
//
//                val respon = response.body()!!
//
//                if(respon.success == 1){
//                    //berhasil
//                    s.setStatusLogin(true)
//                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//                    startActivity(intent)
//                    finish()
//                    Toast.makeText(this@RegisterActivity, "Success: "+respon.message +" Selamat Datang "+respon.user.name, Toast.LENGTH_LONG).show()
//                }else {
//                    //gagal
//                    Toast.makeText(this@RegisterActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
//    }
}