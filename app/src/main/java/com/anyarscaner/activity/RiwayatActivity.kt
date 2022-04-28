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
import com.anyarscaner.app.ApiConfig
import com.anyarscaner.helper.SharedPref
import com.anyarscaner.model.ResponModel
import com.anyarscaner.model.RiwayatModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatActivity : AppCompatActivity() {

    lateinit var s: SharedPref
    lateinit var tv_user :TextView
    lateinit var tv_email : TextView
    lateinit var tv_poin_user : TextView

    lateinit var rvRiw : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        s = SharedPref(this)
        tv_user = findViewById(R.id.txt_nama)
        tv_poin_user = findViewById(R.id.tv_totalPoin)
        tv_email= findViewById(R.id.txt_email)

        header()

        val pb = findViewById<ProgressBar>(R.id.pb_riwayat)

        pb.visibility = View.VISIBLE


        ApiConfig.instanceRetrofit.his_sn(s.getString(s.nama)).enqueue(object :
            Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {

                pb.visibility = View.GONE
                val respon = response.body()!!

                if(respon.success == 1){
                    listRiws = respon.riws
                    displayData()
                }else{
                    //gagal
                    Toast.makeText(this@RiwayatActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText(this@RiwayatActivity, "Terjadi Kesalahan: "+t.message, Toast.LENGTH_SHORT).show()
            }

        })

        toolbar()

    }

    private fun toolbar() {
        //set Tollbar
        setSupportActionBar(findViewById(R.id.tollbar))
        supportActionBar!!.title = "Riwayat Scanner"
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun header() {
        val user = s.getUser()!!
        tv_user.text = user.name
        tv_email.text = user.email

        ApiConfig.instanceRetrofit.totalPoin(tv_user.text.toString()).enqueue(object : Callback<ResponModel>{
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                s.setString(s.total_poin, respon.total_poin).toString()

                if (respon.success == 1) {

                    val getPoin = s.getString(s.total_poin)

                    tv_poin_user.text = getPoin
                }
//                Toast.makeText(this@RiwayatActivity, "Success: "+respon.message, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(this@RiwayatActivity, "Terjadi Kesalahan: "+t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private var listRiws: ArrayList<RiwayatModel> = ArrayList()

    fun displayData(){
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rvRiw = findViewById(R.id.rv_riwayat)
        rvRiw.layoutManager = layoutManager

        rvRiw.adapter = AdapterHis(this, listRiws)
        rvRiw.layoutManager = layoutManager
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}