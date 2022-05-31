package com.anyarscaner.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anyarscaner.MainActivity
import com.anyarscaner.R
import com.anyarscaner.activity.ScanBarcodeActivity
import com.anyarscaner.adapter.AdapterCariSn
import com.anyarscaner.api.ApiConfig
import com.anyarscaner.api.ApiService
import com.anyarscaner.helper.SharedPref
import com.anyarscaner.model.ResponModel
import com.anyarscaner.model.SnModel
import com.anyarscaner.model.User
import com.budiyev.android.codescanner.CodeScanner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScannerFragment: Fragment() {
    private lateinit var s: SharedPref

    private lateinit var user : User

    private lateinit var list_sn: List<SnModel>
    private lateinit var adapter: Adapter
    private lateinit var apiInterface: ApiService

    //    lateinit var pb:ProgressBar
    private lateinit var rv_sn: RecyclerView
    private lateinit var txt_sn: TextView
    private lateinit var tv_user: TextView
    private lateinit var btn_carisn :Button

//    lateinit var tv_sn:TextView
//    lateinit var tv_user:TextView

    private var recyclerDataSnArrayList: ArrayList<SnModel>? = null


//    ########################## Batas kode Lama
    private lateinit var codeScanner: CodeScanner
    private  lateinit var edt_cari: EditText
    private lateinit var btn_cari: Button

//    private lateinit var btn_scan: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_scanner, container, false)

        s = SharedPref(requireActivity())

        mainButton(view)
        setData(view)

        return view

        //        Dialog dialogLogout = (Dialog) view.findViewById(R.id.field_logout);
//        btn_cari = requireView().findViewById<Button>(R.id.btn_sn_manual)


//        btn_cari.setOnClickListener(View.OnClickListener {
//            val inData = Intent(activity, CariSNActivity::class.java)
//            startActivity(inData)
//        })
//        mainButton()

    }
    private var listSn: ArrayList<SnModel> = ArrayList()

    private fun setData(view: View) {
        tv_user = view.findViewById(R.id.tv_user)
        tv_user.text = s.getUser()?.name
    }

    fun displayData(view: View) {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rv_sn = view.findViewById(R.id.rv_sn)
        rv_sn.layoutManager = layoutManager

        rv_sn.adapter = AdapterCariSn(requireActivity(), listSn)
        rv_sn.layoutManager = layoutManager
    }

    private fun mainButton(view: View) {
        val btn_carisn = view.findViewById<Button>(R.id.btn_cariSN)
        val btn_scan = view.findViewById<Button>(R.id.btn_scan_sn)
        val btn_kembali = view.findViewById<ImageView>(R.id.btn_kembali)

        btn_kembali.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        btn_carisn.setOnClickListener {
            cari_sn(view)
        }

        btn_scan.setOnClickListener {
            val intent = Intent(activity, ScanBarcodeActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    fun cari_sn(view: View) {
        val edt_sn = view.findViewById<EditText>(R.id.tv_sn)

        if (edt_sn.text.isEmpty()) {
            edt_sn.error = "Kolom SN atau Kode unik tidak boleh kosong."
            edt_sn.requestFocus()
            return
//        }else if (edt_sn.text.length < 10){
//            edt_sn.error = "SN kurang dari 10 Karakter"
//            edt_sn.requestFocus()
//            return
//        }else if (edt_sn.text.length > 10){
//            edt_sn.error = "SN Lebih dari 10 Karakter"
//            edt_sn.requestFocus()
//            return
        }else{
            val pb = view.findViewById<ProgressBar>(R.id.pb_search)

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
//                        displayData(view)
//                        //finish()
//                    } else {
//                        //gagal
//                        Toast.makeText(
//                            requireActivity(),
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
//                        requireActivity(),
//                        "Terjadi Kesalahan: " + t.message,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//            })

//        saveriwayat()
        }
    }

//    private fun mainButton() {
//        //Inisialisasi ID (Button)
//        val btnLogin = findViewById<Button>(R.id.btn_ProsesLogin)
//        val btnRegist = findViewById<TextView>(R.id.tv_regist)
//
//        btnLogin.setOnClickListener {
//            startActivity(Intent(this, LoginActivity::class.java))
//        }
//
//        btnRegist.setOnClickListener{
//            startActivity(Intent(this, RegisterActivity::class.java))
//        }
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val scannerView = view.findViewById<CodeScannerView>(R.id.scn)
//        val activity = requireActivity()
//        codeScanner = CodeScanner(activity, scannerView)
//        codeScanner.decodeCallback = DecodeCallback {
//            activity.runOnUiThread {
//                Toast.makeText(activity, it.text, Toast.LENGTH_LONG).show()
//            }
//        }
//        scannerView.setOnClickListener {
//            codeScanner.startPreview()
//        }
//    }

//    override fun onResume() {
//        super.onResume()
//        codeScanner.startPreview()
//    }
//
//    override fun onPause() {
//        codeScanner.releaseResources()
//        super.onPause()
//    }
}