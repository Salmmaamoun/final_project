package com.example.data.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LoginRegiisterRetrofitInstance {
    private const val TOKEN = "19|2fl6hKslOFb7dVAHr904AEECOY25KjdpQL8Io1hz"
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
        Log.d("massge2", level.toString())
    }
    private val tokenInterceptor = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $TOKEN")
            .build()
        chain.proceed(newRequest)
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(tokenInterceptor)
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://shubramasrshops.000webhostapp.com/api/v1/")
        .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
        .build()


    fun getApi(): ApiService? {
        Log.e("massge2", HttpLoggingInterceptor.Level.BODY.toString())
        return retrofit?.create(ApiService::class.java)
    }

}