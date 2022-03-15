package com.anyarscaner.model

class User {
    lateinit var email:String
    lateinit var name:String
    lateinit var phone:String
    lateinit var norek:String
    lateinit var nama_bank:String
    lateinit var atas_nama:String
    lateinit var nama_akun_ol:String
    var id : Int = 0

    @JvmName("getId1")
    fun getId() : Int {
        return id
    }

    @JvmName("setId1")
    fun setId(id : Int?){
        if (id != null) {
            this.id= id
        }
    }

    @JvmName("getEmail1")
    fun getEmail(): String? {
        return email
    }

    @JvmName("setEmail1")
    fun setEmail(email: String?) {
        if (email != null) {
            this.email = email
        }
    }

    @JvmName("getName1")
    fun getName(): String?{
        return name
    }

    @JvmName("setName1")
    fun setName(name : String?){
        if (name != null) {
            this.name = name
        }
    }

    fun getPone(): String?{
        return phone
    }

    @JvmName("setPhone1")
    fun setPhone(phone : String?){
        if (phone != null) {
            this.phone = phone
        }
    }

    @JvmName("getNorek1")
    fun getNorek(): String?{
        return norek
    }

    @JvmName("setNorek1")
    fun setNorek(norek : String?){
        if (norek != null) {
            this.norek = norek
        }
    }

    fun getBank() : String?{
        return nama_bank
    }

    fun setBank(nama_bank :String?){
        if (nama_bank != null) {
            this.nama_bank = nama_bank
        }
    }

    fun getAtsnama() : String?{
        return atas_nama
    }

    fun setAtasnama(atas_nama : String?){
        if (atas_nama != null) {
            this.atas_nama = atas_nama
        }
    }

    fun getAkunol(): String? {
        return nama_akun_ol
    }

    fun setAkunol(akunOl : String?){
        if (akunOl != null) {
            this.nama_akun_ol = akunOl
        }
    }


}