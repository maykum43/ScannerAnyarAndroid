package com.anyarscaner.model

import com.google.gson.annotations.SerializedName

data class PostResponse(
//    var success = 1,
//    var error = 0,
    val message:String?,
    val total_poin: String?,
    var jumlah : String?,
    @SerializedName("body")
    val text: String?
)
