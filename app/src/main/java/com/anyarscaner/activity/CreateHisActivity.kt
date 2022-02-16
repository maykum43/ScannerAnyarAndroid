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

class CreateHisActivity : AppCompatActivity(){

    lateinit var s: SharedPref
    lateinit var tv_sn : TextView
    lateinit var tv_id : TextView
    lateinit var btn_proses : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_his)
        btn_proses = findViewById(R.id.btn_proses)

        getData()





//
//        tv_sn = findViewById(R.id.tv_sn)
//
//        //meletakan source yang dibawa
//        tv_sn.setText(intent.getStringExtra("sn"))
    }

    fun getData(){
        s = SharedPref(this)

        tv_id = findViewById(R.id.tv_namaUser)
        tv_id.text = s.getString(s.nama)


        val sn = intent.getStringExtra("sn")
        tv_sn = findViewById(R.id.tv_sn)
        tv_sn.text = sn

        btn_proses.setOnClickListener{
            ApiConfig.instanceRetrofit.create_his(tv_sn.text.toString(),tv_id.text.toString()).enqueue(object :
                Callback<ResponModel> {
                override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {

                    val respon = response.body()!!

                    if(respon.success == 1){
                        val intent = Intent(this@CreateHisActivity, ScannerActivity::class.java)
                        startActivity(intent)
//                        finish()
                        Toast.makeText(this@CreateHisActivity, "Success: "+respon.message, Toast.LENGTH_LONG).show()
                        //finish()
                    }else{
                        //gagal
                        Toast.makeText(this@CreateHisActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                    val intent = Intent(this@CreateHisActivity, ScannerActivity::class.java)
                    startActivity(intent)
//                    Toast.makeText(this@CreateHisActivity, "Ditemukan Kesalahan: "+t.message, Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@CreateHisActivity, "Selamat Voucher Cashback: Diproses ", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }

    fun prosesData(){

        val edt_sn = findViewById<EditText>(R.id.tv_sn)
        val edt_id = findViewById<EditText>(R.id.tv_namaUser)

        ApiConfig.instanceRetrofit.create_his(edt_sn.text.toString(),edt_id.text.toString()).enqueue(object :
            Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                if(respon.success == 1){
                    val intent = Intent(this@CreateHisActivity, ScannerActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this@CreateHisActivity,"Sukses : "+respon.message,Toast.LENGTH_LONG).show()
                    finish()
                    //finish()
                }else{
                    //gagal
//                    Toast.makeText(this@CreateHisActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {

            }
        })
    }


}