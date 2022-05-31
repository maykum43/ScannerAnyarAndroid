package com.anyarscaner.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object ApiConfig {
    //    private const val BASE_URL = "http://vouchercashback.cctvbandung.co.id/public/api/"
//    private const val BASE_URL = "http://192.168.68.95/WebAdminScanner2/public/api/"
//    private const val BASE_URL = "http://192.168.1.235/WebAdminScanner2/public/api/" //Url Wifi GPT
//        private const val BASE_URL = "http://192.168.100.38/WebAdminScanner2/public/api/" //Url Wifi Dela
    const val BASE_URL = "https://gpt.poin.cctvbandung.co.id/public/api/"  //Link Online
    //    private const val BASE_URL = "http://192.168.100.38/WebAdminScanner2/public/api/"
//    private val client: Retrofit
//        get(){
//            val gson = GsonBuilder().setLenient().create()
//            val interceptor = HttpLoggingInterceptor()
//            interceptor.level = HttpLoggingInterceptor.Level.BODY
//            val client: OkHttpClient = OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .connectTimeout(40, TimeUnit.SECONDS)
//                .readTimeout(40, TimeUnit.SECONDS)
//                .writeTimeout(40, TimeUnit.SECONDS)
//                .build()
//
//            return Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .client(client)
//                .build()
//        }
//
//    val instanceRetrofit: ApiService
//        get() = client.create(ApiService::class.java)

//    #################API 2#################
const val baseUrl = "https://gpt.poin.cctvbandung.co.id/public/api/"

    private val client : Retrofit

    get() {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client:OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(40,TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    val instanceRetrofit : ApiService
    get() = client.create(ApiService::class.java)





//    fun getRetrofit() : Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    fun getService() : ApiService{
//        return getRetrofit().create(ApiService::class.java)
//    }

}
