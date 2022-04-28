package com.anyarscaner.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.anyarscaner.R
import com.anyarscaner.app.ApiConfig
import com.anyarscaner.helper.SharedPref
import com.anyarscaner.model.HadiahModel
import com.anyarscaner.model.ResponModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailHadiahActivity : AppCompatActivity() {

    lateinit var s: SharedPref

//    Data User
    lateinit var tv_user : TextView
    lateinit var tv_email : TextView
    lateinit var tv_poin_user : TextView

//    Data Hadiah
    lateinit var tv_title: TextView
    lateinit var tv_poin: TextView
    lateinit var tv_stok: TextView
    lateinit var iv_hadiah: ImageView
    lateinit var toolbar : Toolbar
    lateinit var btn_redeem : Button

    lateinit var pb_totalPoin : ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hadiah)

        layout( this)

        getInfo()
    }

    fun layout(view: DetailHadiahActivity) {
        s = SharedPref(this)
//        Data Hadiah
        tv_title = findViewById(R.id.tv_tittle)
        tv_poin = findViewById(R.id.tv_poin)
        iv_hadiah = findViewById(R.id.image)
        tv_stok = findViewById(R.id.tv_stokHadiah)
        btn_redeem = findViewById(R.id.btn_redeem)

//        Data User
        tv_user = findViewById(R.id.txt_nama)
        tv_email = findViewById(R.id.txt_email)
        tv_poin_user = findViewById(R.id.tv_totalPoin)
        pb_totalPoin = findViewById(R.id.pb_totalPoin)
//
////        Set Data User
        val user = s.getUser()!!
        tv_user.text = user.name
        tv_email.text = user.email

        pb_totalPoin.visibility = View.VISIBLE
        tv_poin_user.text = ApiConfig.instanceRetrofit.totalPoin(tv_user.text.toString()).enqueue(object : Callback<ResponModel>{
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                s.setString(s.total_poin, respon.total_poin).toString()

                pb_totalPoin.visibility = View.GONE

                if (respon.success == 1) {
                    val getPoin = s.getString(s.total_poin)
                    tv_poin_user.text = getPoin
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(this@DetailHadiahActivity, "Terjadi Kesalahan: "+t.message, Toast.LENGTH_SHORT).show()
            }
        }).toString()

        if (tv_poin.toString() > tv_poin_user.toString()){
            btn_redeem.setEnabled(false)
        }
    }

    fun getInfo(){
        val name = intent.getStringExtra("name")
        val req_poin = intent.getStringExtra("req_poin")
        val foto = intent.getStringExtra("foto")
        val stok = intent.getStringExtra("stok")

        //set value
        tv_title.text = name
        tv_poin.text = req_poin
        tv_stok.text = stok

        val img = "https://gpt.poin.cctvbandung.co.id/public/storage/Hadiah/FotoHadiah/" + foto
        Picasso.get()
            .load(img)
            .placeholder(R.drawable.hadiah_default)
            .error(R.drawable.hadiah_default)
            .into(iv_hadiah)
//
//        //set Tollbar
        setSupportActionBar(findViewById(R.id.tollbar))
        supportActionBar!!.title = name
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        redeemPoin()
    }

    private fun redeemPoin() {
        s = SharedPref(this)
        btn_redeem.setOnClickListener {
            val pb = findViewById<ProgressBar>(R.id.pb_createRed)
            pb.visibility = View.GONE

            ApiConfig.instanceRetrofit.create_redPoin(s.getString(s.nama),tv_title.text.toString()).enqueue(object :
                Callback<ResponModel> {
                override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                    pb.visibility = View.GONE
                    val respon = response.body()!!
                    if(respon.success == 1){
                        val intent = Intent(this@DetailHadiahActivity, PoinMallActivity::class.java)
                        startActivity(intent)
//                        finish()
                        Toast.makeText(this@DetailHadiahActivity, ""+respon.message, Toast.LENGTH_LONG).show()
                        //finish()
                    }else{
                        //gagal
                        Toast.makeText(this@DetailHadiahActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                    val intent = Intent(this@DetailHadiahActivity, PoinMallActivity::class.java)
                    startActivity(intent)
                    pb.visibility = View.GONE
                    Toast.makeText(this@DetailHadiahActivity, "Ditemukan Kesalahan: "+t.message, Toast.LENGTH_SHORT).show()
//                    Toast.makeText(this@DetailHadiahActivity, "Selamat Voucher Cashback: Diproses ", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
