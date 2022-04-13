package com.anyarscaner.model

import java.io.Serializable

class HadiahModel : Serializable {
    var id: Int = 0
    lateinit var name:String
    lateinit var req_poin:String
    lateinit var foto : String
    var stok : Int = 0

    @JvmName("getName1")
    fun getName(): String ?{
        return name
    }

    @JvmName("setName1")
    fun setName(name:String?){
        if (name != null) {
            this.name = name
        }
    }

    fun getPoin(): String ?{
        return req_poin
    }

    fun setPoin(req_poin:String?){
        if (req_poin != null) {
            this.req_poin = req_poin
        }
    }

    @JvmName("getFoto1")
    fun getFoto(): String? {
        return foto
    }

    @JvmName("setFoto1")
    fun setFoto(foto:String?){
        if (foto != null) {
            this.foto = foto
        }
    }
}
