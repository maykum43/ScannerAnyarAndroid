package com.anyarscaner.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.anyarscaner.R
import com.anyarscaner.api.ApiConfig
import com.anyarscaner.helper.SharedPref
import com.anyarscaner.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateHisActivity : AppCompatActivity(){

    lateinit var s: SharedPref
    private lateinit var tv_sn : TextView
    private lateinit var tv_id : TextView
    private lateinit var tv_email : TextView
    lateinit var tv_poin_user : TextView

    private lateinit var btn_proses : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_his)
        s = SharedPref(this)
        btn_proses = findViewById(R.id.btn_proses)
        getData()

        setSupportActionBar(findViewById(R.id.tollbar))
        supportActionBar!!.title = "Cek Serial Number"
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


//        tv_sn = findViewById(R.id.tv_sn)
//
//        //meletakan source yang dibawa
//        tv_sn.setText(intent.getStringExtra("sn"))
    }

    fun getData(){

        tv_id = findViewById(R.id.txt_nama)
        tv_id.setText(s.getString(s.name))

        tv_email = findViewById(R.id.txt_email)
        tv_email.setText(s.getString(s.email))

        tv_poin_user = findViewById(R.id.tv_totalPoin)
        tv_poin_user.setText(s.getString(s.total_poin))

        val sn = intent.getStringExtra("sn")
        tv_sn = findViewById(R.id.tv_sn)
        tv_sn.text = sn

        btn_proses.setOnClickListener{
            val pb = findViewById<ProgressBar>(R.id.pb_createHis)

            pb.visibility = View.VISIBLE
            ApiConfig.instanceRetrofit.create_his(tv_sn.text.toString(),tv_email.text.toString()).enqueue(object :
                Callback<ResponModel> {
                override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                    pb.visibility = View.GONE
                    val respon = response.body()!!
                    if(respon.success == 1){
                        val intent = Intent(this@CreateHisActivity, ScanBarcodeActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent)
//                        finish()
                        Toast.makeText(this@CreateHisActivity, " "+respon.message, Toast.LENGTH_LONG).show()
                        //finish()
                    }else{
                        //gagal
                        Toast.makeText(this@CreateHisActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                    val intent = Intent(this@CreateHisActivity, ScanBarcodeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent)
                    pb.visibility = View.GONE
//                    Toast.makeText(this@CreateHisActivity, "Ditemukan Kesalahan: "+t.message, Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@CreateHisActivity, "Selamat Voucher Cashback: Diproses ", Toast.LENGTH_SHORT).show()
//                    Toast.makeText(this@CreateHisActivity, "Mohon maaf. SN bukan dari kami", Toast.LENGTH_SHORT).show()
                }

            })
        }

    }

    fun prosesData(){

        val edt_sn = findViewById<EditText>(R.id.tv_sn)
        val edt_email = findViewById<EditText>(R.id.tv_namaUser)

//        ApiConfig.instanceRetrofit.create_his(edt_sn.text.toString(),edt_email.text.toString()).enqueue(object :
//            Callback<ResponModel> {
//            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
//                val respon = response.body()!!
//                if(respon.success == 1){
//                    val intent = Intent(this@CreateHisActivity, ScannerActivity::class.java)
//                    startActivity(intent)
//                    Toast.makeText(this@CreateHisActivity,"Sukses : "+respon.message,Toast.LENGTH_LONG).show()
//                    finish()
//                    //finish()
//                }else{
//                    //gagal
////                    Toast.makeText(this@CreateHisActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
//
//            }
//        })
    }

    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
        val intent = Intent(this@CreateHisActivity, ScanBarcodeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
//        finish()
        return super.onSupportNavigateUp()
    }

}