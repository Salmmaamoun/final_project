package com.example.domain.entity

import com.google.gson.annotations.SerializedName

data class TafseerResponse(

	@field:SerializedName("data")
	val data: List<TafseerDataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class TafseerDataItem(

	@field:SerializedName("ayah")
	val ayah: Ayah? = null,

	@field:SerializedName("e1dition_id")
	val editionId: Int? = null,

	@field:SerializedName("data")
	val data: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Ayah(

	@field:SerializedName("juz_id")
	val juzId: Int? = null,

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("surah_id")
	val surahId: Int? = null,

	@field:SerializedName("number_in_surah")
	val numberInSurah: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("hizb_id")
	val hizbId: Int? = null,

	@field:SerializedName("sajda")
	val sajda: Int? = null
)
