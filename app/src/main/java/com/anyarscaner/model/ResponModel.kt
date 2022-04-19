package com.anyarscaner.model

class ResponModel {
    var success = 1
    var error = 0
    lateinit var message:String
    lateinit var TotalPoin : String
    var jmlRedPoin : Int = 0

    var user = User()
    var sns : ArrayList<SnModel> = ArrayList()
    var riws : ArrayList<RiwayatModel> = ArrayList()
    var hadiahs: ArrayList<HadiahModel> = ArrayList()
    var riwred: ArrayList<RiwRedModel> = ArrayList()
//    var poin =

}