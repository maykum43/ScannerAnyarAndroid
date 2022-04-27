package com.anyarscaner.model

class ResponModel {
    var success = 1
    var error = 0
    lateinit var message:String
    lateinit var total_poin: String
    lateinit var jumlah : String

    var user = User()
//    var poin = PoinModel()
    var sns : ArrayList<SnModel> = ArrayList()
    var riws : ArrayList<RiwayatModel> = ArrayList()
    var hadiahs: ArrayList<HadiahModel> = ArrayList()
    var riwred : ArrayList<RiwayatRedeemModel> = ArrayList()
}