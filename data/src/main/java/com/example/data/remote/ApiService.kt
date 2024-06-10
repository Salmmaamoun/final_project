package com.example.data.remote


import com.example.domain.entity.LexicalResponse
import com.example.domain.entity.LexicalResponseItem
import com.example.domain.entity.LoginResponse
import com.example.domain.entity.RegisterResponse
import com.example.domain.entity.SurahResponse
import com.example.domain.entity.TafseerResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("auth/login")
    @Multipart
    suspend fun login(
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody
    ):LoginResponse

    @POST("auth/register")
    @Multipart
    suspend fun registerUser(
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("password_confirmation")password_confirmation:RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part image: MultipartBody.Part?
    ): RegisterResponse
    @GET("surahs")
    suspend fun getSurahs(
        @Query("lang") lang: String):
           SurahResponse

    @GET("editions/{editionId}")
    suspend fun getAyah(
        @Path("editionId") editionId: Int,
        @Query("surah") surahNumber: Int,
        @Query("ayah") ayahNumber: Int
    ):TafseerResponse

    @GET("search")
    suspend fun getSearchLexical(@Query("term") term: String):List<LexicalResponseItem>

}