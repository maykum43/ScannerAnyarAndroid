package com.anyarscaneroffline.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anyarscaneroffline.R
import com.anyarscaneroffline.adapter.AdapterRiwRed
import com.anyarscaneroffline.api.ApiConfig
import com.anyarscaneroffline.helper.SharedPref
import com.anyarscaneroffline.model.ResponModel
import com.anyarscaneroffline.model.RiwayatRedeemModel
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
                    listRiwRed = respon.riwayat_redeem
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
        tv_jmlHadiah.text = s.getString(s.jml_redeem)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}