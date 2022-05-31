package com.anyarscaner.responseModel

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("success")
	val success: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("user")
	val user: User? = null
)

var user = User()

data class User(

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("email_verified_at")
	val emailVerifiedAt: Any? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("foto")
	val foto: Any? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("norek")
	val norek: String? = null,

	@field:SerializedName("nama_akun_ol")
	val namaAkunOl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("nama_bank")
	val namaBank: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("atas_nama")
	val atasNama: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
