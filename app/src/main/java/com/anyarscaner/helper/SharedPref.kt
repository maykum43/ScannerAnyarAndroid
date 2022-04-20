package com.anyarscaner.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.anyarscaner.model.User
import com.google.gson.Gson

class SharedPref(activity: Activity) {
    val login = "login"
    val cari_sn = "cari_sn"

    var id = "id"
    val nama = "nama"
    val email = "email"
    val phone = "phone"
    val norek = "norek"
    val nama_bank = "nama_bank"
    val atas_nama = "atas_nama"
    val akun_ol ="nama_akun_ol"
    val pasword = "password"
    val alamat = "alamat"

    var poin = "poin"

    val sn = "sn"

    val mypref = "MAIN_PREF"

    val user = "user"

    val sp: SharedPreferences

    init{
        sp = activity.getSharedPreferences(mypref, Context.MODE_PRIVATE)
    }

    fun setStatusLogin(status:Boolean){
        sp.edit().putBoolean(login,status).apply()
    }

    fun getStatusLogin():Boolean{
        return  sp.getBoolean(login, false)
    }

    fun setString(key: String, value: String) {
        sp.edit().putString(key, value).apply()
    }

    fun getString(key: String):String{
        return  sp.getString(key, "")!!
    }

    fun setInt(key: String, value: Int){
        sp.edit().putInt(key, value)
    }

    fun getInt(key: String):Int{
        return sp.getInt(key,0)
    }

    fun setUser(value: String) {
        val data: String = Gson().toJson(value, User::class.java)
        sp.edit().putString(user, data).apply()
    }
}
//
//private fun Any.putInt(i: Int, value: Int): SharedPreferences.Editor? {
//    TODO("Not yet implemented")
//}