package com.anyarscaner.activity

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.anyarscaner.R
import com.budiyev.android.codescanner.*

class ScanBarcodeActivity : AppCompatActivity() {

    lateinit var codeScanner : CodeScanner
//    var scn = findViewById<CodeScanner>(R.id.scn)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_barcode)

        setupPermissions()
        codeScanner()
    }

    fun codeScanner(){
//        val scn = findViewById<CodeScanner>(R.id.scn)


        val scn = findViewById<CodeScannerView>(R.id.scn)

        codeScanner = CodeScanner(this,scn)

        val tvHasil = findViewById<TextView>(R.id.tv_hasil)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS

            isAutoFocusEnabled = true
            isFlashEnabled = true

            decodeCallback = DecodeCallback {
                runOnUiThread {
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
}