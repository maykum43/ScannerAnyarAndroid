package com.anyarscaner.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.anyarscaner.R
import com.anyarscaner.activity.BantuanActivity
import com.anyarscaner.activity.RiwayatActivity

class SupportFragment : Fragment() {
    lateinit var  btnBantuan: RelativeLayout
    lateinit var btnGmail : ImageButton
//    lateinit var  btnEmail: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_support, container, false)
//        init(view)

        klikBantuan(view)
        klikGmail(view)
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

    fun klikGmail(view: View){
        btnGmail =view.findViewById(R.id.btn_gmail_admin)

        btnGmail.setOnClickListener {
            val email = "admin@cctvbandung.co.id"
            val subject = "Bantuan Aplikasi Voucher Cashback"
            val pesan = "Halo support GPT, saya user aplikasi Voucher Cashback GPT, boleh minta bantuanya.(boleh dijelasin keluhanya kenapa)"

            val recipients = email.split(",".toRegex()).toTypedArray()

            val i = Intent(Intent.ACTION_SEND)
            i.putExtra(Intent.EXTRA_EMAIL, recipients)
            i.putExtra(Intent.EXTRA_SUBJECT, subject)
            i.putExtra(Intent.EXTRA_TEXT, pesan)

            i.type = "message/rfc822"
            this.startActivity(Intent.createChooser(i, "Pilih aplikasi Email"))
        }
    }
}