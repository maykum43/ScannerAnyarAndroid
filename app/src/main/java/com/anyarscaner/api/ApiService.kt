package com.anyarscaner.api

import com.anyarscaner.model.ResponModel
import com.anyarscaner.responseModel.ResponseLogin
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String,
        @Field("alamat") alamat:String,
        @Field("phone") nomortlp:String,
        @Field("norek") norek:String,
        @Field("nama_bank") bank:String,
        @Field("atas_nama") atas_nama:String,
        @Field("nama_akun_ol") akun_ol:String
//        @Field("foto") foto:String
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
    fun getRiws(
        @Field("email") name: String
    ) : Call<ResponModel>

    @FormUrlEncoded
    @POST("redPoin")
    fun create_redPoin(
        @Field("email") email: String,
        @Field("name") name:String
    ) : Call<ResponModel>

    @FormUrlEncoded
    @PUT("update_user/{email}")
    fun  edit_profil(
//        @Field("id") id:Int,
        @Path("email") string: String,
//        @Field("email") email:String,
        @Field("name") name:String? = null,
        @Field("phone") nomortlp:String? = null,
        @Field("norek") norek:String? = null,
        @Field("nama_bank") bank:String? = null,
        @Field("atas_nama") atas_nama:String? = null,
        @Field("nama_akun_ol") akun_ol:String? = null,
    ) : Call<ResponModel>

    @FormUrlEncoded
    @POST("cari_pelanggan")
    fun  cari_pelangan(
        @Field("id") id:Int,
    ) : Call<ResponModel>

    @FormUrlEncoded
    @POST("totalPoin")
    fun totalPoin(
        @Field("email") email:String,
    ) : Call<ResponModel>


    @FormUrlEncoded
    @POST("riwred")
    fun jumlah(
        @Field("email") email:String,
    ) : Call<ResponModel>


    @POST("hadiahs")
    fun getHadiah() : Call<ResponModel>

    @FormUrlEncoded
    @POST("create_his")
    fun create_his(
        @Field("sn") sn:String,
        @Field("user") user:String
    ) : Call<ResponModel>

    @FormUrlEncoded
    @POST("riwred")
    fun getRiwRed(
        @Field("email") email: String
    ) : Call<ResponModel>

}