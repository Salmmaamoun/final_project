package com.example.data.remote

import com.example.domain.entity.SemanticAiResponce
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AiService {
    @GET("similar-verse/{query}")
    suspend fun getSemanticSearch(
        @Path("query") query: String
    ):SemanticAiResponce

    @GET("similar-verse/{query}")
    suspend fun getSemaSearch(
        @Path("query") query: String
    ): Response<ResponseBody>
}
/*
 @GET("similar-verse/{query}")
    suspend fun getSemanticSearch(
        @Path("query") query: String
    ):SemanticAiResponce
 */