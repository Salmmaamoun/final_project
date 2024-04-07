package com.example.domain.entity

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val gender: String,
    val phone: String,
    val image: ByteArray?
)
