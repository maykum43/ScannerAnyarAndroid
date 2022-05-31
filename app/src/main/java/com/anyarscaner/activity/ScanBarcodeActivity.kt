package com.anyarscaner.activity

//import com.anyarscaner.databinding.ActivityScannerBinding
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.anyarscaner.MainActivity
import com.anyarscaner.R
import com.anyarscaner.api.ApiConfig
import com.anyarscaner.model.ResponModel
import com.budiyev.android.codescanner.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScanBarcodeActivity : AppCompatActivity() {

    lateinit var codeScanner : CodeScanner
    lateinit var btnProses : Button
    lateinit var tvHasil : TextView

    lateinit var pbScan : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_barcode)
        tvHasil = findViewById(R.id.tv_hasil)

        setupPermissions()
        codeScanner()

        //set Tollbar
        setSupportActionBar(findViewById(R.id.tollbar))
        supportActionBar!!.title = "Scan Barcode Serial Number"
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun codeScanner(){
//        val scn = findViewById<CodeScanner>(R.id.scn)

        btnProses =  findViewById<Button>(R.id.btn_cariBarcode)

        val scn = findViewById<CodeScannerView>(R.id.scn)

        codeScanner = CodeScanner(this,scn)

//        val tvHasil = findViewById<TextView>(R.id.tv_hasil)
        pbScan = findViewById(R.id.pb_scan)
        pbScan.visibility = View.VISIBLE

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS

            isAutoFocusEnabled = true
            isFlashEnabled = true

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    pbScan.visibility = View.GONE
                    tvHasil.text = it.text
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    Log.e("Scanner","Terjadi Kesalahan :  ${it.message}")
                }
            }

            scn.setOnClickListener{
                codeScanner.startPreview()
            }

        }

        btnProses.setOnClickListener {
            apiCari()
        }
    }

    private fun apiCari() {
//        val tvHasil = findViewById<TextView>(R.id.tv_hasil)

        ApiConfig.instanceRetrofit.cari_sn(tvHasil.text.toString()).enqueue(object :
            Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                //Response Berhasil
//                pb.visibility = View.GONE
                val respon = response.body()!!

                if(respon.success == 1){
//                    pb.visibility = View.GONE
                    Toast.makeText(this@ScanBarcodeActivity, "Barcode Berhasil Ditemukan", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@ScanBarcodeActivity, CreateHisActivity::class.java)
                    intent.putExtra("sn",tvHasil.text)
                    startActivity(intent)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    finish()
                }else{
                    //gagal
                    Toast.makeText(this@ScanBarcodeActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                //Response Gagal
//                pb.visibility = View.GONE
//                Toast.makeText(this@ScanBarcodeActivity, "Voucher Sudah digunakan atau SN Bukan dari kami. ", Toast.LENGTH_SHORT).show()
                Toast.makeText(this@ScanBarcodeActivity, "Error: "+t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        val CAMERA_REQ = 0
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.CAMERA), CAMERA_REQ
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val CAMERA_REQ = 0

        when (requestCode) {
            CAMERA_REQ -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                        this,
                        "Memerlukan izin untuk menggunakan Kamera",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
        val intent = Intent(this@ScanBarcodeActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
//        finish()
        return super.onSupportNavigateUp()
    }

    private var exitTime: Long = 0
    override fun onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 8000) {
            Toast.makeText(this, "Klik kembali untuk keluar", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
//        super.onBackPressed()
    }
}