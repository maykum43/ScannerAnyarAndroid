package com.anyarscaner.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.anyarscaner.R
import com.anyarscaner.activity.EditProfilActivity
import com.anyarscaner.activity.RiwayatActivity
import com.anyarscaner.helper.SharedPref

class ProfilFragment : Fragment() {
    lateinit var s: SharedPref
    lateinit var btnLogout: TextView
    lateinit var txt_nama: TextView
    lateinit var txt_email: TextView
    lateinit var btn_editprofil : RelativeLayout
    lateinit var btn_riwayat : RelativeLayout
    lateinit var btn_about : RelativeLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profil, container, false)
        init(view)

        s = SharedPref(requireActivity())

        btnLogout.setOnClickListener{
            s.setStatusLogin(false)
        }

        setData()
        button(view)
        return view
    }

    private fun button(view: View) {
        btn_editprofil = view.findViewById(R.id.rl_editprofil)
        btn_riwayat = view.findViewById(R.id.rl_riwayat)
        btn_about = view.findViewById(R.id.rl_aboutus)

        btn_riwayat.setOnClickListener {
            val inData = Intent(activity, RiwayatActivity::class.java)
            startActivity(inData)
        }

        btn_editprofil.setOnClickListener {
            val inData =Intent(activity, EditProfilActivity::class.java)
            startActivity(inData)
        }
    }

    private fun init(view: View) {
        btnLogout = view.findViewById(R.id.btn_Logout)
        txt_nama = view.findViewById(R.id.txt_nama)
        txt_email = view.findViewById(R.id.txt_email)
    }

    fun setData(){
        txt_nama.text = s.getString(s.nama)
        txt_email.text = s.getString(s.email)
    }
}