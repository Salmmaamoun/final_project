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
        .connectTimeout(30, TimeUnit.SECONDS)  // Increase connection timeout
        .readTimeout(30, TimeUnit.SECONDS)     // Increase read timeout
        .writeTimeout(30, TimeUnit.SECONDS)    // Increase write timeout
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://shubramasrshops.000webhostapp.com/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun getApi(): ApiService? {
        return retrofit.create(ApiService::class.java)
    }
}
