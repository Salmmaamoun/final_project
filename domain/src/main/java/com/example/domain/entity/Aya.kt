package com.example.domain.entity

data class Aya(
    val id: Int,
    val number: Int,
    val number_in_surah: Int,
    val surah_id: Int,
    val juz_id: Int,
    val hizb_id: Int,
    val page: Int,
    val sajda: Int,
    val text: String,
    val textWithouttashkeel: String,
    val audio: String,
    val created_at: String,
    val updated_at: String
)