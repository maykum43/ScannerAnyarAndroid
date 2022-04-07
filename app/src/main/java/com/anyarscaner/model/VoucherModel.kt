package com.anyarscaner.model

import java.io.Serializable

class VoucherModel : Serializable {
    lateinit var title : String
    var poin : Int = 0
    var image_voucher :Int = 0
    lateinit var ket : String
}