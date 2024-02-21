package com.example.domain.entity

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("errors")
	val errors: RegisterErrors? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)

data class RegisterErrors(

	@field:SerializedName("password")
	val password: List<String?>? = null,

	@field:SerializedName("password_confirmation")
	val password_confirmation: List<String?>? = null,

	@field:SerializedName("gender")
	val gender: List<String?>? = null,

	@field:SerializedName("phone")
	val phone: List<String?>? = null,

	@field:SerializedName("name")
	val name: List<String?>? = null,

	@field:SerializedName("email")
	val email: List<String?>? = null
)
