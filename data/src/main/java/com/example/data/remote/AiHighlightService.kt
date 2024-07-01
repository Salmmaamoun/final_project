package com.example.data.remote

import com.example.domain.entity.HighlightResponce
import com.example.domain.entity.QuestionRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface AiHighlightService {

    @POST("question")
    @FormUrlEncoded
    suspend fun askQuestion(
        @Field("question") question: String,
        @Field("context") context: String
    ): HighlightResponce
}