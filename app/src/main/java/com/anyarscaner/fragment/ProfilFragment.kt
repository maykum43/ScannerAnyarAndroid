package com.anyarscaner.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ContentInfoCompat
import androidx.fragment.app.Fragment
import com.anyarscaner.R
import com.anyarscaner.activity.AboutActivity
import com.anyarscaner.activity.EditProfilActivity
import com.anyarscaner.activity.LoginActivity
import com.anyarscaner.activity.RiwayatActivity
import com.anyarscaner.helper.SharedPref
import org.w3c.dom.Text

class ProfilFragment : Fragment() {
    lateinit var s: SharedPref
    lateinit var btnLogout: TextView

    lateinit var txt_nama: TextView
    lateinit var txt_email: TextView
//    lateinit var txt_phone: TextView
//    lateinit var txt_norek:TextView
//    lateinit var txt_namaBank: TextView
//    lateinit var txt_atasNama: TextView
//    lateinit var txt_akunOl: TextView

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
            val inData = Intent(activity, LoginActivity::class.java)
            inData.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            Toast.makeText(getActivity(),"Anda Logout dari akun anda",Toast.LENGTH_SHORT).show();
            startActivity(inData)
            onStop()
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

        btn_about.setOnClickListener {
            val inData =Intent(activity, AboutActivity::class.java)
            startActivity(inData)
        }
    }

    private fun init(view: View) {
        btnLogout = view.findViewById(R.id.btn_Logout)
        txt_nama = view.findViewById(R.id.txt_nama)
        txt_email = view.findViewById(R.id.txt_email)
//        txt_phone= view.findViewById(R.id.txt_phone)
//        txt_norek = view.findViewById(R.id.txt_norek)
//        txt_namaBank = view.findViewById(R.id.txt_namaBank)
//        txt_atasNama = view.findViewById(R.id.txt_atas_nama)
//        txt_akunOl = view.findViewById(R.id.txt_akun_ol)
    }

    fun setData(){
        txt_nama.text = s.getString(s.nama)
        txt_email.text = s.getString(s.email)
//        txt_phone.text = s.getString(s.phone)
//        txt_norek.text = s.getString(s.norek)
//        txt_namaBank.text = s.getString(s.nama_bank)
//        txt_atasNama.text = s.getString(s.atas_nama)
//        txt_akunOl.text = s.getString(s.akun_ol)
    }
}