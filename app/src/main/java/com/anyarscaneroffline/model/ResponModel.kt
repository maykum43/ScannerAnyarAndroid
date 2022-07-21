package com.anyarscaneroffline.model

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

        val TotalPoin:String? = null
        val riwayat:String? = null
        val jml_redeem:String? =null
        val ststusReed:String = "0"

//        val name:String = "name"
//        val email:String = "email"
//        val phone:String = "phone"
//        val norek:String = "norek"
//        val bank:String = "bank"
//        val ats_nama:String = "ats_nama"
//        val akun_ol:String = "akun_ol"
//        val status:String = "status"
//        val alamat:String = "alamat"
//
//        val TotalPoin:String = "TotalPoin"
//        val riwayat:String = "riwayat"
//        val jml_redeem:String = "jml_redeem"
//        val ststusReed:String = "0"

        var riws : ArrayList<RiwayatModel> = ArrayList()
        var hadiahs: ArrayList<HadiahModel> = ArrayList()
        var riwayat_redeem : ArrayList<RiwayatRedeemModel> = ArrayList()
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