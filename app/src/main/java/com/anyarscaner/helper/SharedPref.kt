package com.anyarscaner.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.anyarscaner.model.PoinModel
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

    val total_poin = "total_poin"
    val jumlah = "jumlah"

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

    fun setUser(value: User) {
        val data: String = Gson().toJson(value, User::class.java)
        sp.edit().putString(user, data).apply()
    }

    fun getUser(): User?{
        val data:String = sp.getString(user, null) ?: return null
        return Gson().fromJson<User>(data, User::class.java)
    }

    fun setPoin(value: PoinModel){
        val data : String = Gson().toJson(value, PoinModel::class.java)
        sp.edit().putString(total_poin, data).apply()
    }

    fun getPoin(): PoinModel?{
        val data:String = sp.getString(total_poin, null) ?: return null
        return Gson().fromJson<PoinModel>(data, PoinModel::class.java)
    }
}