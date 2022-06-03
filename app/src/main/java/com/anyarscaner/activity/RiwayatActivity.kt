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
import com.anyarscaner.api.ApiConfig
import com.anyarscaner.helper.SharedPref
import com.anyarscaner.model.ResponModel
import com.anyarscaner.model.RiwayatModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatActivity : AppCompatActivity() {
    lateinit var s : SharedPref

    lateinit var tv_user: TextView
    lateinit var tv_email: TextView
    lateinit var tv_totalPoin: TextView

    lateinit var rvRiws: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        s = SharedPref(this)
        tv_user = findViewById(R.id.txt_nama)
        tv_email = findViewById(R.id.txt_email)
        tv_totalPoin = findViewById(R.id.tv_totalPoin)

        header()

        val pb = findViewById<ProgressBar>(R.id.pb_riwayat)

        pb.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.getRiws(tv_email.text.toString()).enqueue(object :Callback<ResponModel>{
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                pb.visibility = View.GONE
                val respon = response.body()!!

                if (respon.success == 1){
                    lisRiws = respon.riws
                    displayData()
                }else{
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

    private fun displayData(){
        val LayoutManagerRiw = LinearLayoutManager(this)
        LayoutManagerRiw.orientation = LinearLayoutManager.VERTICAL

        rvRiws = findViewById(R.id.rv_riwayat)
        rvRiws.layoutManager = LayoutManagerRiw

        rvRiws.adapter = AdapterHis(this, lisRiws)
        rvRiws.layoutManager = LayoutManagerRiw
    }

    private fun toolbar(){
        setSupportActionBar(findViewById(R.id.tollbar))
        supportActionBar!!.title = "Riwayat Scan Serial Number"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    private var lisRiws: ArrayList<RiwayatModel> = ArrayList()

    private fun header(){
//        val user = s.getUser()!!
        tv_user.text = s.getString(s.name)
        tv_email.text = s.getString(s.email)
        tv_totalPoin.text = s.getString(s.total_poin)

//        ApiConfig.instanceRetrofit.totalPoin(tv_user.text.toString()).enqueue(object : Callback<ResponModel>{
//            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
//                val respon = response.body()!!
//                s.setString(s.total_poin, respon.total_poin.toString())
//
//                if (respon.success == 1) {
//
//                    val getPoin = s.getSting(s.total_poin)
//
//                    tv_totalPoin.text = getPoin
//                }
////                Toast.makeText(this@RiwayatActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
//                Toast.makeText(this@RiwayatActivity, "Terjadi Kesalahan: "+t.message, Toast.LENGTH_SHORT).show()
//            }
//        }).toString()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}