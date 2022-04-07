package com.anyarscaner.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
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

    lateinit var btn_editprofil : ImageView
    lateinit var btn_riwayat : RelativeLayout
    lateinit var btn_poinMall : RelativeLayout
    lateinit var btn_about : RelativeLayout
    lateinit var btn_logout : RelativeLayout


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

//            var id = user.getId()
//            val tv_nama = user.name
//            val tv_email = user.email
//            val tv_phone = user.phone
//            val tv_norek = user.norek
//            val tv_bank = user.nama_bank
//            val tv_atasnama = user.atas_nama
//            val tv_akunOl = user.akun_ol
//
//            ApiConfig.instanceRetrofit.cari_pelangan(id).enqueue(object :
//                Callback<ResponModel> {
//                override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
//                    val respon = response.body()!!
//
//                    if(respon.success == 1){
//                        val intent = Intent(activity, NewEditProfilActivity::class.java)
//                        intent.putExtra("name",tv_nama)
//                        intent.putExtra("email",tv_email)
//                        intent.putExtra("phone",tv_phone)
//                        intent.putExtra("norek",tv_norek)
//                        intent.putExtra("bank",tv_bank)
//                        intent.putExtra("atasnama",tv_atasnama)
//                        intent.putExtra("akunOl",tv_akunOl)
//
//                        startActivity(intent)
//                    }else{
//                        Toast.makeText(activity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<ResponModel>, t: Throwable) {
//                    Toast.makeText(activity, "Error: "+t.message, Toast.LENGTH_SHORT).show()
//                }
//
//            })
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

    }

    private fun init(view: View) {
        txt_nama = view.findViewById(R.id.txt_nama)
        txt_email = view.findViewById(R.id.txt_email)
        txt_poin = view.findViewById(R.id.tv_totalPoin)
    }

    fun setData(){
        txt_nama.text = s.getString(s.nama)
        txt_email.text = s.getString(s.email)
        txt_poin.text = ApiConfig.instanceRetrofit.totalPoin(s.getString(s.nama)).enqueue(object : Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                    if(respon.success == 1){
                        txt_poin.setText(respon.TotalPoin)
                    }else{
                        Toast.makeText(activity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                    }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {

            }
        })
            .toString()
    }

//    fun setPoin(){
//        ApiConfig.instanceRetrofit.totalPoin(txt_nama.toString()).enqueue(object : Callback<ResponModel> {
//                override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
//                    val respon = response.body()!!
//
//                    if(respon.success == 1){
//                        txt_poin.setText(respon.TotalPoin)
//                    }else{
//                        Toast.makeText(activity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
//                    }
//                }
//                override fun onFailure(call: Call<ResponModel>, t: Throwable) {
//                    Toast.makeText(activity, "Error: "+t.message, Toast.LENGTH_SHORT).show()
//                }
//        })
//    }
}