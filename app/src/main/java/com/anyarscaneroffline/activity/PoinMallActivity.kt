package com.anyarscaneroffline.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anyarscaneroffline.R
import com.anyarscaneroffline.adapter.AdapterVoucher
import com.anyarscaneroffline.api.ApiConfig
import com.anyarscaneroffline.helper.SharedPref
import com.anyarscaneroffline.model.HadiahModel
import com.anyarscaneroffline.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PoinMallActivity : AppCompatActivity() {

    lateinit var s: SharedPref
    lateinit var tv_user : TextView
    lateinit var tv_email : TextView
    lateinit var tv_poin : TextView

    lateinit var pbJmlPoin : ProgressBar
    lateinit var pbHadiah : ProgressBar

    lateinit var rvHadiah : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poin_mall)
        s = SharedPref(this)
        init(this)

//        val pb = findViewById<ProgressBar>(R.id.pb_hadiah)
//
//        pb.visibility = View.VISIBLE
        getHadiah()
        setData()
    }

    fun displayHadiah(){
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rvHadiah.adapter = AdapterVoucher(this, listHadiah)
        rvHadiah.layoutManager = layoutManager
    }
    private var listHadiah: ArrayList<HadiahModel> = ArrayList()

    fun  getHadiah(){
        pbHadiah.visibility = View.VISIBLE
//list hadiah
        ApiConfig.instanceRetrofit.getHadiah().enqueue(object :
            Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val res = response.body()!!
                pbHadiah.visibility = View.GONE
                if (res.success == 1){
                    listHadiah = res.hadiahs
                    displayHadiah()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {

            }

        })
    }

    fun init(view: PoinMallActivity){
        rvHadiah = findViewById(R.id.rv_voucher)
        pbJmlPoin = findViewById(R.id.pb_totalPoin1)
        pbHadiah = findViewById(R.id.pb_hadiah)

        setSupportActionBar(findViewById(R.id.tollbar))
        supportActionBar!!.title = "Redeem Poin"
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun setData(){
//        val user = s.getUser()!!

        tv_user = findViewById(R.id.txt_nama)
        tv_user.text = s.getString(s.name)

        tv_email= findViewById(R.id.txt_email)
        tv_email.text = s.getString(s.email)

        tv_poin = findViewById(R.id.tv_totalPoin)
        tv_poin.text = s.getString(s.TotalPoin)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}