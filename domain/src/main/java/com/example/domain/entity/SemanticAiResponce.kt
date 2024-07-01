package com.example.domain.entity

data class SemanticAiResponce(
    val results: List<List<Any>> = listOf(),
)

data class QuranAyah(
    val surahId: Long,
    val ayahId: Long,
    val text: String
)

//val results: List<Triple<Long, Long, String>> = listOf(),