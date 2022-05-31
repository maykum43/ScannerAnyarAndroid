package com.anyarscaner.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import com.anyarscaner.model.PoinModel
import com.anyarscaner.model.User
import com.google.gson.Gson

class SharedPref(activity: FragmentActivity) {
    val login = "login"
    val myPref = "Main_Pref"
    val sharedPreference : SharedPreferences

    val user = "user"

//    Data Percobaan
    val name = "name"
    val email = "email"
    val phone = "phone"
    val norek = "norek"
    val bank = "nama_bank"
    val ats_nama = "atas_nama"
    val akun_ol = "nama_akun_ol"
    val status = "status"
    val alamat = "alamat"

    val total_poin = "total_poin"
    val jumlah = "jumlah"
    val statusReed = "status"


    init {
        sharedPreference = activity.getSharedPreferences(myPref, Context.MODE_PRIVATE)
    }

    fun setStatusLogin(status : Boolean){
        sharedPreference.edit().putBoolean(login, status).apply()
    }

    fun getStatusLogin() : Boolean{
        return sharedPreference.getBoolean(login, false)
    }

    fun getUser(): User?{
        val data:String = sharedPreference.getString(user, null) ?: return null
        return Gson().fromJson<User>(data, User::class.java)
    }

    fun setUser(value: User) {
        val data: String = Gson().toJson(value, User::class.java)
        sharedPreference.edit().putString(user, data).apply()
    }

    fun setString(key: String, value: String){
        sharedPreference.edit().putString(key, value).apply()
    }

    fun getString(key: String): String{
        return sharedPreference.getString(key,"")!!
    }

}