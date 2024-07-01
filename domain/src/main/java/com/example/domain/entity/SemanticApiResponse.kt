package com.example.domain.entity

import com.google.gson.annotations.SerializedName

data class SemanticApiResponse(
    @SerializedName("data")
    val ayaData: AyaData? = null
)

data class AyaData(
    val audio: String? = null,
    val audio1: String? = null,
    val audio2: String? = null,
    val hizbQuarter: Int? = null,
    val juz: Int? = null,
    val numberInQuran: Int? = null,
    val numberInSurah: Int? = null,
    val page: Int? = null,
    val sajda: Boolean? = null,
    val surah: String? = null,
    val verse: String? = null,
    val verseWithoutTashkeel: String? = null,
    val verse_pk: String? = null,

    var isHighligthed :Boolean=false,
    var highligthedResponse :HighlightResponce? =null
)