package com.anyarscaner.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.anyarscaner.R
import com.anyarscaner.activity.*
import com.anyarscaner.databinding.FragmentHomeBinding
import com.anyarscaner.databinding.FragmentProfilBinding
import com.anyarscaner.helper.SharedPref
import org.w3c.dom.Text

class ProfilFragment : Fragment() {
    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!
    private lateinit var s: SharedPref

    lateinit var tv_nama: TextView
    lateinit var tv_email: TextView
    lateinit var tv_poin: TextView
    lateinit var tv_jml_redeem: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfilBinding.inflate(inflater, container ,false)

        s = SharedPref(requireActivity())

        header(binding.root)
        button(binding.root)
        setData()

        return binding.root
    }

    private fun button(view: View) {
        val btn_riwayat = view.findViewById<RelativeLayout>(R.id.rl_riwayat)
        val btn_poinMall = view.findViewById<RelativeLayout>(R.id.rl_poinMall)
        val btn_about = view.findViewById<RelativeLayout>(R.id.rl_aboutus)
        val btn_logout = view.findViewById<RelativeLayout>(R.id.rl_logout)
        val btn_editprofil = view.findViewById<ImageView>(R.id.iv_profil)
        val btn_poin = view.findViewById<ConstraintLayout>(R.id.cv_poin)
        val btn_redeem= view.findViewById<ConstraintLayout>(R.id.cv_redeem)

        btn_riwayat.setOnClickListener {
            val inData = Intent(activity, RiwayatActivity::class.java)
            startActivity(inData)
        }

        btn_poinMall.setOnClickListener {
            val inData = Intent(activity, PoinMallActivity::class.java)
            startActivity(inData)
        }

        btn_editprofil.setOnClickListener {
            val inData = Intent(activity, EditProfilActivity::class.java)
            startActivity(inData)
        }

        btn_about.setOnClickListener {
            val inData = Intent(activity, AboutActivity::class.java)
            startActivity(inData)
        }

        btn_logout.setOnClickListener{
            s.setStatusLogin(false)
            val inData = Intent(activity, LoginActivity::class.java)
            inData.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            inData.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            Toast.makeText(requireActivity(),"Anda Logout dari akun anda", Toast.LENGTH_SHORT).show();
            startActivity(inData)
        }

        btn_poin.setOnClickListener {
            val inData = Intent(activity, RiwayatActivity::class.java)
            startActivity(inData)
        }

        btn_redeem.setOnClickListener {
            val inData = Intent(activity, RiwayatRedeemActivity::class.java)
            startActivity(inData)
        }
    }

    private fun header(view: View) {
        tv_nama = view.findViewById(R.id.txt_nama)
        tv_email = view.findViewById(R.id.txt_email)
        tv_poin = view.findViewById(R.id.tv_totalPoin)
        tv_jml_redeem = view.findViewById(R.id.tv_jml_redeem)
    }

    private fun setData(){
        tv_nama.text = s.getString(s.name)
        tv_email.text= s.getString(s.email)
        tv_poin.text = s.getString(s.total_poin)
        tv_jml_redeem.text = s.getString(s.jumlah)
    }
}