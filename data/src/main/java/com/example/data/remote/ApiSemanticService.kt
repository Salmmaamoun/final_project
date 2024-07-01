package com.example.data.remote

import com.example.domain.entity.AyaData
import com.example.domain.entity.SemanticApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiSemanticService {
    @GET("api/lexical/verse-id")
    suspend fun getSemanticAyaById(@Query("id") id: Int): SemanticApiResponse

}