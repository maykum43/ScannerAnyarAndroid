package com.anyarscaner.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anyarscaner.R
import com.anyarscaner.adapter.AdapterRiwRed
import com.anyarscaner.api.ApiConfig
import com.anyarscaner.helper.SharedPref
import com.anyarscaner.model.ResponModel
import com.anyarscaner.model.RiwayatRedeemModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatRedeemActivity:AppCompatActivity() {
    lateinit var s: SharedPref

    lateinit var tv_user : TextView
    lateinit var tv_email : TextView
    lateinit var tv_jmlHadiah : TextView

    lateinit var rvRiwRed : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat_redeem)

        s = SharedPref(this)
        tv_user = findViewById(R.id.txt_nama)
        tv_email= findViewById(R.id.txt_email)
        tv_jmlHadiah= findViewById(R.id.tv_jml_redeem)

        header()

        val pb = findViewById<ProgressBar>(R.id.pb_riwayat_redeem)

        pb.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.getRiwRed(s.getString(s.email)).enqueue(object : Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                pb.visibility = View.GONE
                val respon = response.body()!!

                if(respon.success == 1){
                    listRiwRed = respon.riwred
                    displayData()
                }else{
                    //gagal
                    Toast.makeText(this@RiwayatRedeemActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText(this@RiwayatRedeemActivity, "Terjadi Kesalahan: "+t.message, Toast.LENGTH_SHORT).show()
            }

        })

        toolbar()

    }

    private fun displayData() {
        val layoutManagerRR = LinearLayoutManager(this)
        layoutManagerRR.orientation = LinearLayoutManager.VERTICAL

        rvRiwRed = findViewById(R.id.rv_riwayat_redeem)
        rvRiwRed.layoutManager = layoutManagerRR

        rvRiwRed.adapter = AdapterRiwRed(this, listRiwRed)
        rvRiwRed.layoutManager = layoutManagerRR

    }

    private fun toolbar() {
        //set Tollbar
        setSupportActionBar(findViewById(R.id.tollbar))
        supportActionBar!!.title = "Riwayat Redeem Poin"
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private var listRiwRed: ArrayList<RiwayatRedeemModel> = ArrayList()

    private fun header() {
//        val user = s.getUser()!!
        tv_user.text = s.getString(s.name)
        tv_email.text = s.getString(s.email)
        tv_jmlHadiah.text = s.getString(s.jumlah)

//        tv_jmlHadiah.text = ApiConfig.instanceRetrofit.jumlah(tv_user.text.toString()).enqueue(object : Callback<ResponModel> {
//            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
//                val respon = response.body()!!
//                s.setString(s.jumlah, respon.jumlah).toString()
//
//                if(respon.success == 1){
//                    val getJumlah = s.getString(s.jumlah)
//                    tv_jmlHadiah.text = getJumlah
//                }else{
//                    Toast.makeText(this@RiwayatRedeemActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
//                Toast.makeText(this@RiwayatRedeemActivity, "Error: "+t.message, Toast.LENGTH_SHORT).show()
//            }
//        }).toString()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}