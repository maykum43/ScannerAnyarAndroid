package com.anyarscaner.app

import com.anyarscaner.model.ResponModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("phone") nomortlp:String,
        @Field("norek") norek:String,
        @Field("nama_bank") bank:String,
        @Field("atas_nama") atas_nama:String,
        @Field("nama_akun_ol") akun_ol:String,
        @Field("password") password:String
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email:String,
        @Field("password") password:String
    ) : Call<ResponModel>

    @FormUrlEncoded
    @POST("cari_sn")
    fun  cari_sn(
        @Field("cari_sn") sn:String
    ) : Call<ResponModel>

    @FormUrlEncoded
    @POST("rwt_sn")
    fun his_sn(
        @Field("his_sn") user: String
    ) : Call<ResponModel>

    @FormUrlEncoded
    @POST("create_his")
    fun create_his(
        @Field("sn") sn:String,
        @Field("user") user:String
    ) : Call<ResponModel>

    @FormUrlEncoded
    @POST("edit_user/{id}")
    fun  edit_profil(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("phone") nomortlp:String,
        @Field("norek") norek:String,
        @Field("nama_bank") bank:String,
        @Field("atas_nama") atas_nama:String,
        @Field("nama_akun_ol") akun_ol:String,
    ) : Call<ResponModel>
}