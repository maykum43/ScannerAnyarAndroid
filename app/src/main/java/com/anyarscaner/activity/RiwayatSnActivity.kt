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
import com.anyarscaner.adapter.AdapterCariSn
import com.anyarscaner.adapter.AdapterRiwayat
import com.anyarscaner.app.ApiConfig
import com.anyarscaner.helper.SharedPref
import com.anyarscaner.model.ResponModel
import com.anyarscaner.model.RiwayatModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatSnActivity : AppCompatActivity() {
    lateinit var s: SharedPref
    lateinit var txt_user:TextView

    lateinit var rv_riw: RecyclerView

    private var listRiw: ArrayList<RiwayatModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        s = SharedPref(this)

//        val pb = findViewById<ProgressBar>(R.id.pb_cariSN)

        txt_user = findViewById(R.id.tv_user)
        txt_user.text = s.getString(s.nama)

        ApiConfig.instanceRetrofit.his_sn(txt_user.text.toString()).enqueue(object :
            Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                //Response Berhasil
//                pb.visibility = View.GONE
                val respon = response.body()!!

                if(respon.success == 1){
//                    pb.visibility = View.GONE
                    Toast.makeText(this@RiwayatSnActivity, "Data Berhasil Ditemukan ", Toast.LENGTH_SHORT).show()
                    listRiw = respon.riws
                    displayData()
                    //finish()
                }else{
                    //gagal
                    Toast.makeText(this@RiwayatSnActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@RiwayatSnActivity, "Belum Ada riwayat", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                //Response Gagal
//                pb.visibility = View.GONE
            }

        })

    }

    fun displayData(){
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rv_riw = findViewById(R.id.rv_riwayat)
        rv_riw.layoutManager = layoutManager

        rv_riw.adapter = AdapterRiwayat(this, listRiw)
        rv_riw.layoutManager = layoutManager
    }
}