package com.example.domain.entity

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("errors")
	val errors: LoginErrors? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)

data class LoginErrors(

	@field:SerializedName("password")
	val password: List<String?>? = null,

	@field:SerializedName("email")
	val email: List<String?>? = null
)
