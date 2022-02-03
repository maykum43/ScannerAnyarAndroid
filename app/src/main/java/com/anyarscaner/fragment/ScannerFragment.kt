package com.anyarscaner.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.anyarscaner.R
import com.anyarscaner.activity.CariSNActivity
import com.budiyev.android.codescanner.CodeScanner

class ScannerFragment: Fragment() {

    private lateinit var codeScanner: CodeScanner
    private  lateinit var edt_cari: EditText
    private lateinit var btn_cari: Button

    private lateinit var btn_scan: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scanner, container, false)

        //        Dialog dialogLogout = (Dialog) view.findViewById(R.id.field_logout);
//        btn_cari = requireView().findViewById<Button>(R.id.btn_sn_manual)


//        btn_cari.setOnClickListener(View.OnClickListener {
//            val inData = Intent(activity, CariSNActivity::class.java)
//            startActivity(inData)
//        })
//        mainButton()

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