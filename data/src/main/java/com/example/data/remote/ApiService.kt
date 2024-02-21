package com.example.data.remote

import com.example.domain.entity.LoginRequest
import com.example.domain.entity.RegisterRequest
import com.example.domain.entity.LoginResponse
import com.example.domain.entity.RegisterResponse
import com.example.domain.entity.SurahResponse
import com.example.domain.entity.TafseerResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("auth/login")
    suspend fun login(
        @Body request:LoginRequest,
    ):LoginResponse

    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): RegisterResponse
    @GET("surahs")
    suspend fun getSurahs(
        @Query("lang") lang: String):
           SurahResponse

    @GET("editions/{editionId}")
    suspend fun getAyah(
        @Path("editionId") editionId: Int,
        @Query("surah_id") surahNumber: Int,
        @Query("number_in_surah") ayahNumber: Int
    ):TafseerResponse

}