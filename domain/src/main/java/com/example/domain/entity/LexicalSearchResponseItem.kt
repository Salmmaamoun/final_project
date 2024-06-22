package com.example.domain.entity

data class LexicalSearchResponseItem(
    val ayahs: List<Aya>,
    val created_at: String,
    val id: Int,
    val name_ar: String,
    val name_en: String,
    val name_en_translation: String,
    val number: Int,
    val type: String,
    val updated_at: String
)