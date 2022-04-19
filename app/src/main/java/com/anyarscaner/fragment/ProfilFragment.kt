package com.anyarscaner.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.anyarscaner.R
import com.anyarscaner.activity.*
import com.anyarscaner.app.ApiConfig
import com.anyarscaner.helper.SharedPref
import com.anyarscaner.model.ResponModel
import com.anyarscaner.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilFragment : Fragment() {
    lateinit var s: SharedPref

    private lateinit var user : User


    lateinit var txt_nama: TextView
    lateinit var txt_email: TextView
    lateinit var txt_poin: TextView
    lateinit var txt_jml_hadiah: TextView

    lateinit var btn_editprofil : ImageView
    lateinit var btn_riwayat : RelativeLayout
    lateinit var btn_poinMall : RelativeLayout
    lateinit var btn_about : RelativeLayout
    lateinit var btn_logout : RelativeLayout

    lateinit var pbPoin : ProgressBar
    lateinit var pbJmlRed :ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profil, container, false)

        init(view)

        s = SharedPref(requireActivity())

        setData()
//        setPoin()
        button(view)
        return view
    }

    private fun button(view: View) {
        btn_editprofil = view.findViewById(R.id.iv_edit_profil)
        btn_riwayat = view.findViewById(R.id.rl_riwayat)
        btn_poinMall = view.findViewById(R.id.rl_poinMall)
        btn_about = view.findViewById(R.id.rl_aboutus)
        btn_logout = view.findViewById(R.id.rl_logout)

        btn_riwayat.setOnClickListener {
            val inData = Intent(activity, RiwayatActivity::class.java)
            startActivity(inData)
        }

        btn_poinMall.setOnClickListener {
            val inData = Intent(activity, PoinMallActivity::class.java)
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

        btn_logout.setOnClickListener{
            s.setStatusLogin(false)
            val inData = Intent(activity, LoginActivity::class.java)
            inData.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            inData.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            Toast.makeText(requireActivity(),"Anda Logout dari akun anda",Toast.LENGTH_SHORT).show();
            startActivity(inData)
        }

        txt_poin.setOnClickListener {
            val inData = Intent(activity, RiwayatActivity::class.java)
            startActivity(inData)
        }

        txt_jml_hadiah.setOnClickListener {
            val inData = Intent(activity, RiwayatRedeem::class.java)
            startActivity(inData)
        }

    }

    private fun init(view: View) {
        txt_nama = view.findViewById(R.id.txt_nama)
        txt_email = view.findViewById(R.id.txt_email)
        txt_poin = view.findViewById(R.id.tv_totalPoin)
        txt_jml_hadiah = view.findViewById(R.id.tv_jml_redem)
        pbPoin = view.findViewById(R.id.pb_totalPoin)
        pbJmlRed = view.findViewById(R.id.pb_jmlRedeem)
    }

    fun setData(){
        txt_nama.text = s.getString(s.nama)
        txt_email.text = s.getString(s.email)

        pbPoin.visibility = View.VISIBLE
        txt_poin.text = ApiConfig.instanceRetrofit.totalPoin(s.getString(s.nama)).enqueue(object : Callback<ResponModel>{
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                if (respon.success == 1){
                    pbPoin.visibility = View.GONE
                    txt_poin.setText(respon.TotalPoin)
                }else{
                    Toast.makeText(activity,"Error : "+respon.message, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(activity,"Terjadi Kesalahan : "+t.message, Toast.LENGTH_LONG).show()
            }

        }).toString()
//        txt_poin.text = ApiConfig.instanceRetrofit.totalPoin(s.getString(s.nama)).enqueue(object :
//            Callback<ResponModel> {
//            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
//                val respon = response.body()!!
//                if(respon.success == 1){
//                    txt_poin.setText(respon.TotalPoin)
//                }else{
//                    Toast.makeText(activity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
//                Toast.makeText(activity, "Error: "+t.message, Toast.LENGTH_SHORT).show()
//            }
//
//        }).toString()

        pbJmlRed.visibility = View.VISIBLE
        txt_jml_hadiah.text = ApiConfig.instanceRetrofit.jmlHadiah(s.getString(s.nama)).enqueue(object : Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                pbJmlRed.visibility = View.GONE
                if(respon.success == 1){
                    txt_jml_hadiah.setText(respon.jmlRedPoin.toString())
                }else{
                    Toast.makeText(activity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(activity, "Error: "+t.message, Toast.LENGTH_SHORT).show()
            }
        }).toString()

    }
}