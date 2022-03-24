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

    lateinit var rvRiw : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        s = SharedPref(this)

        tv_user = findViewById(R.id.txt_nama)
        tv_user.text = s.getString(s.nama)

        tv_email= findViewById(R.id.txt_email)
        tv_email.text = s.getString(s.email)

        val pb = findViewById<ProgressBar>(R.id.pb_riwayat)

        pb.visibility = View.VISIBLE


        ApiConfig.instanceRetrofit.his_sn(tv_user.text.toString()).enqueue(object :
            Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {

                pb.visibility = View.GONE
                val respon = response.body()!!

                if(respon.success == 1){

                    listRiws = respon.riws
                    displayData()
//                        finish()
                    //finish()
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
}