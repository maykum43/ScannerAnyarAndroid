package com.anyarscaner.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.anyarscaner.R
import com.anyarscaner.api.ApiConfig
import com.anyarscaner.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LupassActivity : AppCompatActivity() {
    private lateinit var email : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lupass)

        val btnLink = findViewById<TextView>(R.id.btn_kirimLink)
        btnLink.setOnClickListener {
            luppas()
        }



        //set Tollbar
        setSupportActionBar(findViewById(R.id.tollbar))
        supportActionBar!!.title = "Lupa Password"
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun luppas() {
        email = findViewById(R.id.tv_email_lupass)
        if (email.text.isEmpty()) {
            email.error = "Kolom email tidak boleh kosong!"
            email.requestFocus()
            return
        }
        val pb = findViewById<ProgressBar>(R.id.pb_lupass)
        pb.visibility = View.VISIBLE

        ApiConfig.instanceRetrofit.lupass(email.text.toString()).enqueue(object :Callback<ResponModel>{
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                pb.visibility = View.GONE
                val respon = response.body()!!
                if(respon.success == 1){
                    Toast.makeText(this@LupassActivity, "Success: "+respon.message, Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@LupassActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(applicationContext,t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
        val intent = Intent(this@LupassActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
        return super.onSupportNavigateUp()
    }
}