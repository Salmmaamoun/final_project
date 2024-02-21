package com.example.domain.entity

import com.google.gson.annotations.SerializedName

data class SurahResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataItem(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("first_page")
	val firstPage: Int? = null,

	@field:SerializedName("last_page")
	val lastPage: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("name_translation")
	val nameTranslation: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null
)
