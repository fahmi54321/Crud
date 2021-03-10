package com.mobile.crud.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseUsers(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class ResultItem(

	@field:SerializedName("jk")
	val jk: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("agama")
	val agama: String? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null,

	@field:SerializedName("hobi")
	val hobi: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
) : Parcelable
