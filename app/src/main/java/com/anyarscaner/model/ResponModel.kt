package com.anyarscaner.model

    class ResponModel {
        val success = 1
        var error = 0
        val message:String? = null

//        Data Percobaan

        val name:String? = null
        val email:String? = null
        val phone:String? = null
        val norek:String? = null
        val bank:String? = null
        val ats_nama:String? = null
        val akun_ol:String? = null
        val status:String? = null
        val alamat:String? = null

        val total_poin:String? = null
        val jumlah:String? = null
        val ststusReed:String? = null

        var riws : ArrayList<RiwayatModel> = ArrayList()
        var hadiahs: ArrayList<HadiahModel> = ArrayList()
        var riwred : ArrayList<RiwayatRedeemModel> = ArrayList()
        var user = User()

//        val user: User? = null




//        lateinit var total_poin: String
//        lateinit var jumlah : String

//        var user = User()
    //    var poin = PoinModel()
//        var sns : ArrayList<SnModel> = ArrayList()
//        var riws : ArrayList<RiwayatModel> = ArrayList()
//        var hadiahs: ArrayList<HadiahModel> = ArrayList()
//        var riwred : ArrayList<RiwayatRedeemModel> = ArrayList()
    }