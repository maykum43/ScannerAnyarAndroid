package com.anyarscaner.model

import java.io.Serializable

class RedeemModel: Serializable {
    lateinit var email : String
    lateinit var jml_poin : String
    lateinit var status : String
    lateinit var nama_hadiah :String
    lateinit var created_at : String
    var poin_awal : Int = 0
    var sisaPoin : Int = 0
}