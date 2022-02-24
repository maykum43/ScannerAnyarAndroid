package com.anyarscaner.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.anyarscaner.R
import com.anyarscaner.activity.BantuanActivity
import com.anyarscaner.activity.RiwayatActivity

class SupportFragment : Fragment() {
    lateinit var  btnBantuan: RelativeLayout
//    lateinit var  btnEmail: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_support, container, false)
//        init(view)

        klikBantuan(view)
        return view
    }

    private fun init(view: View) {
        btnBantuan = view.findViewById(R.id.rl_bantuan)
//        btnEmail = view.findViewById(R.id.txt_nama)
    }

    fun klikBantuan(view: View){
        btnBantuan = view.findViewById(R.id.rl_bantuan)

        btnBantuan.setOnClickListener {
            val inData = Intent(activity, BantuanActivity::class.java)
            startActivity(inData)
        }
    }
}