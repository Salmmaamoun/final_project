package com.example.domain.abstraction.DataSourceRepo

import com.example.domain.entity.LexicalSearchResponse
import com.example.domain.entity.LoginRequest
import com.example.domain.entity.RegisterRequest
import com.example.domain.entity.LoginResponse
import com.example.domain.entity.RegisterResponse
import com.example.domain.entity.SurahResponse
import com.example.domain.entity.TafseerResponse

interface DataSourceRepo {
    suspend fun login(request: LoginRequest): LoginResponse

    suspend fun register( request: RegisterRequest): RegisterResponse
    suspend fun getSurahs(language: String): SurahResponse
    suspend fun getAyah(editionId: Int, surahNumber: Int, ayahNumber: Int): TafseerResponse

    suspend fun getSearchLexical(term : String) :LexicalSearchResponse

}