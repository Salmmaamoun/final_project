package com.example.domain.entity

import com.google.gson.annotations.SerializedName

data class LexicalResponse(
	val lexicalResponse: List<LexicalResponseItem>
)


data class AyahsItem(

	@field:SerializedName("juz_id")
	val juzId: Int? = null,

	@field:SerializedName("textWithouttashkeel")
	val textWithouttashkeel: String? = null,

	@field:SerializedName("number_in_surah")
	val numberInSurah: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("hizb_id")
	val hizbId: Int? = null,

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("surah_id")
	val surahId: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("sajda")
	val sajda: Int? = null,

	@field:SerializedName("audio")
	val audio: String? = null
)

data class LexicalResponseItem(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("name_en_translation")
	val nameEnTranslation: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("name_ar")
	val nameAr: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("ayahs")
	val ayahs: List<AyahsItem?>? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("name_en")
	val nameEn: String? = null
)