package com.anyarscaner.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.anyarscaner.model.User
import com.google.gson.Gson

class SharedPref(activity: Activity) {
    val login = "login"
    val cari_sn = "cari_sn"

    val nama = "nama"
    val email = "email"

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

    fun setUser(value: User) {
        val data: String = Gson().toJson(value, User::class.java)
        sp.edit().putString(user, data).apply()
    }

    fun setString(key: String) {

    }
}