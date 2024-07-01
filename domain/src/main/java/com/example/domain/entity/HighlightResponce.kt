package com.example.domain.entity

data class HighlightResponce(
    val answer: String? = null,
    val end: Int? = null,
    val score: Double? = null,
    val start: Int? = null
)

data class QuestionRequest(
    val question: String,
    val context: String
)

data class QuestionResponse(
    val answer: String
)