package com.example.data.remote

import android.util.Log
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object LoginRegiisterRetrofitInstance {
    private const val TOKEN = "19|2fl6hKslOFb7dVAHr904AEECOY25KjdpQL8Io1hz"
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val tokenInterceptor = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $TOKEN")
            .build()
        chain.proceed(newRequest)
    }

    val okHttpClient = OkHttpClient.Builder()
        .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
        .protocols(listOf(Protocol.HTTP_1_1))
        .addInterceptor(loggingInterceptor)
        .addInterceptor(tokenInterceptor)
        .connectTimeout(200, TimeUnit.SECONDS)  // Set connection timeout
        .readTimeout(200, TimeUnit.SECONDS)     // Set read timeout
        .writeTimeout(200, TimeUnit.SECONDS)    // Set write timeout
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://skyblue-scorpion-935834.hostingersite.com/public/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val retrofitForAi = Retrofit.Builder()
        .baseUrl("https://quran-semantic-api.icycliff-d2b823f1.eastus.azurecontainerapps.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val retrofitForSemanticApi = Retrofit.Builder()
        .baseUrl("https://quranic-api.icycliff-d2b823f1.eastus.azurecontainerapps.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val retrofitForAiHighligth = Retrofit.Builder()
        .baseUrl("https://hugging-api--d7yhkmh.icycliff-d2b823f1.eastus.azurecontainerapps.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun getApi(): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    fun getAiApi(): AiService {
        return retrofitForAi.create(AiService::class.java)
    }

    fun getApiSemSearch(): ApiSemanticService {
        return retrofitForSemanticApi.create(ApiSemanticService::class.java)
    }

    fun getAiApiHighligth(): AiHighlightService {
        return retrofitForAiHighligth.create(AiHighlightService::class.java)
    }
}