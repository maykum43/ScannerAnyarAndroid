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
import com.anyarscaner.adapter.AdapterHis
import com.anyarscaner.adapter.AdapterRiwRed
import com.anyarscaner.app.ApiConfig
import com.anyarscaner.helper.SharedPref
import com.anyarscaner.model.ResponModel
import com.anyarscaner.model.RiwRedModel
import com.anyarscaner.model.RiwayatModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatRedeem : AppCompatActivity() {

    lateinit var s: SharedPref
    lateinit var tv_user :TextView
    lateinit var tv_email : TextView
    lateinit var tv_jml_red : TextView

    lateinit var rvRiwRed : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwred)

        s = SharedPref(this)
        tv_user = findViewById(R.id.txt_nama)
        tv_email= findViewById(R.id.txt_email)
        tv_jml_red = findViewById(R.id.tv_jml_redeem)

        header()
        getRiwred()


        //set Tollbar
        setSupportActionBar(findViewById(R.id.tollbar))
        supportActionBar!!.title = "Riwayat Redeem Poin"
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun header() {
        val user = s.getUser()!!
        tv_user.text = user.name
        tv_email.text = user.email

        ApiConfig.instanceRetrofit.jumlah(s.getString(s.nama)).enqueue(object : Callback<ResponModel>{
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                s.setString(s.jumlah, respon.jumlah).toString()

                if (respon.success == 1) {

                    val getJumlah = s.getString(s.jumlah)

                    tv_jml_red.text = getJumlah
                }
//                Toast.makeText(this@RiwayatRedeem, "Berh: "+respon.message, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(this@RiwayatRedeem, "Terjadi Kesalahan: "+t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private var listRiwRed: ArrayList<RiwRedModel> = ArrayList()

    fun getRiwred(){
        val pb = findViewById<ProgressBar>(R.id.pb_riwred)

        pb.visibility = View.GONE

        ApiConfig.instanceRetrofit.riwred(s.getString(s.nama)).enqueue(object :
            Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!

                if(respon.success == 1){
                    listRiwRed = respon.riwred
                    displayData()
                }
                    //gagal
//                    Toast.makeText(this@RiwayatRedeem, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
//                pb.visibility = View.GONE
                Toast.makeText(this@RiwayatRedeem, "Terjadi Kesalahan: "+t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }



    fun displayData(){
        val layoutManager1 = LinearLayoutManager(this)
        layoutManager1.orientation = LinearLayoutManager.VERTICAL

        rvRiwRed = findViewById(R.id.rv_riwred)
        rvRiwRed.layoutManager = layoutManager1

        rvRiwRed.adapter = AdapterRiwRed(this, listRiwRed)
        rvRiwRed.layoutManager = layoutManager1
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}