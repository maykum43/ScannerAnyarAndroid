package com.anyarscaner.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anyarscaner.MainActivity
import com.anyarscaner.R
import com.anyarscaner.adapter.AdapterCariSn
import com.anyarscaner.api.ApiConfig
import com.anyarscaner.api.ApiService
//import com.anyarscaner.databinding.ActivityScannerBinding
import com.anyarscaner.helper.SharedPref
import com.anyarscaner.model.ResponModel
import com.anyarscaner.model.SnModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ScannerActivity : AppCompatActivity() {

//    private var _binding : ActivityScannerBinding? = null
//    private val binding get() = _binding!!

//    private lateinit var binding: com.anyarscaner.activity.ActivityScannerBinding

    lateinit var s: SharedPref
//    private val api by Lazy { ApiRetrofit().login}

    lateinit var list_sn: List<SnModel>
    lateinit var adapter: Adapter
    lateinit var apiInterface: ApiService

    //    lateinit var pb:ProgressBar
    lateinit var rv_sn: RecyclerView
    lateinit var txt_sn: TextView
    lateinit var tv_user: TextView

//    lateinit var tv_sn:TextView
//    lateinit var tv_user:TextView

    private var recyclerDataSnArrayList: ArrayList<SnModel>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        s = SharedPref(this)

        mainButton()
        setData()

        return
    }

    private var listSn: ArrayList<SnModel> = ArrayList()

    fun displayData() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rv_sn = findViewById(R.id.rv_sn)
        rv_sn.layoutManager = layoutManager

        rv_sn.adapter = AdapterCariSn(this, listSn)
        rv_sn.layoutManager = layoutManager
    }

    fun mainButton() {
        val btn_carisn = findViewById<Button>(R.id.btn_cariSN)
        val btn_scan = findViewById<Button>(R.id.btn_scan_sn)
        val btn_kembali = findViewById<ImageView>(R.id.btn_kembali)

        btn_kembali.setOnClickListener {
            val intent = Intent(this@ScannerActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        btn_carisn.setOnClickListener {
            cari_sn()
        }

        btn_scan.setOnClickListener {
            val intent = Intent(this@ScannerActivity, ScanBarcodeActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }

    fun setData() {
//        txt_sn.text = s.getString(s.sn)
        tv_user = findViewById(R.id.tv_user)

        tv_user.text = s.getUser()?.name
    }


    fun cari_sn() {
        val edt_sn = findViewById<EditText>(R.id.tv_sn)

        if (edt_sn.text.isEmpty()) {
            edt_sn.error = "Kolom SN atau Kode unik tidak boleh kosong."
            edt_sn.requestFocus()
            return
        }else if (edt_sn.text.length < 10){
            edt_sn.error = "SN kurang dari 10 Karakter"
            edt_sn.requestFocus()
            return
        }else if (edt_sn.text.length > 10){
            edt_sn.error = "SN Lebih dari 10 Karakter"
            edt_sn.requestFocus()
            return
        }else{
            val pb = findViewById<ProgressBar>(R.id.pb_search)

            pb.visibility = View.VISIBLE

//            ApiConfig.instanceRetrofit.cari_sn(edt_sn.text.toString()).enqueue(object :
//                Callback<ResponModel> {
//                override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
//                    //Response Berhasil
//                    pb.visibility = View.GONE
//                    val respon = response.body()!!
//
//                    if (respon.success == 1) {
////                    pb.visibility = View.GONE
////                    Toast.makeText(this@ScannerActivity, "Data Berhasil Ditemukan ", Toast.LENGTH_SHORT).show()
//                        listSn = respon.sns
//                        displayData()
//                        //finish()
//                    } else {
//                        //gagal
//                        Toast.makeText(
//                            this@ScannerActivity,
//                            "Error: " + respon.message,
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<ResponModel>, t: Throwable) {
//                    //Response Gagal
//                    pb.visibility = View.GONE
//                    Toast.makeText(
//                        this@ScannerActivity,
//                        "Terjadi Kesalahan: " + t.message,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//            })

//        saveriwayat()
        }
    }
}
