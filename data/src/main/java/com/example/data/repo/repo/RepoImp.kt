package com.example.data.repo.repo

import com.example.data.remote.ApiService
import com.example.domain.abstraction.Repo.Repo
import com.example.domain.entity.LoginRequest
import com.example.domain.entity.RegisterRequest
import com.example.domain.entity.LoginResponse
import com.example.domain.entity.RegisterResponse
import com.example.domain.entity.SurahResponse
import com.example.domain.entity.TafseerResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

class RepoImp(val dataSource: ApiService?):Repo {
    override suspend fun login(request: LoginRequest): LoginResponse {
        val email = RequestBody.create("text/plain".toMediaTypeOrNull(), request.email)
        val password = RequestBody.create("text/plain".toMediaTypeOrNull(), request.password)
        return dataSource!!.login(email, password)
    }

    override suspend fun register(
        request: RegisterRequest
    ): RegisterResponse {
        val name = RequestBody.create("text/plain".toMediaTypeOrNull(), request.name)
        val email = RequestBody.create("text/plain".toMediaTypeOrNull(), request.email)
        val password = RequestBody.create("text/plain".toMediaTypeOrNull(), request.password)
        val passwordcon = RequestBody.create("text/plain".toMediaTypeOrNull(), request.password_confirmation)
        val gender = RequestBody.create("text/plain".toMediaTypeOrNull(), request.gender)
        val phone = RequestBody.create("text/plain".toMediaTypeOrNull(), request.phone)

        val imagePart = request.image?.let {
            val requestImage = RequestBody.create("image/jpeg".toMediaTypeOrNull(), it)
            MultipartBody.Part.createFormData("image", "image.jpg", requestImage)
        }
        return dataSource!!.registerUser(name, email, password, password_confirmation = passwordcon, gender, phone, imagePart)
    }

    override suspend fun getSurahs(language: String): SurahResponse {
        return dataSource!!.getSurahs(language)
    }

    override suspend fun getAyah(
        editionId: Int,
        surahNumber: Int,
        ayahNumber: Int
    ): TafseerResponse {
        return dataSource!!.getAyah(editionId,surahNumber,ayahNumber)
    }


}