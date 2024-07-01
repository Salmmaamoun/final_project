package com.example.domain.abstraction.Repo

import com.example.domain.entity.AyaData
import com.example.domain.entity.HighlightResponce
import com.example.domain.entity.LexicalResponseItem
import com.example.domain.entity.LoginRequest
import com.example.domain.entity.LoginResponse
import com.example.domain.entity.RegisterRequest
import com.example.domain.entity.RegisterResponse
import com.example.domain.entity.SemanticAiResponce
import com.example.domain.entity.SemanticApiResponse
import com.example.domain.entity.SurahResponse
import com.example.domain.entity.TafseerResponse

interface Repo {
    suspend fun login(request:LoginRequest): LoginResponse

    suspend fun register(request:RegisterRequest): RegisterResponse
    suspend fun getSurahs(language: String):SurahResponse
    suspend fun getAyah(editionId: Int, surahNumber: Int, ayahNumber: Int): TafseerResponse

    suspend fun getSearchLexical(term : String) :List<LexicalResponseItem>
    suspend fun getSemanticsearch(term : String) :SemanticAiResponce

    suspend fun getSmanticAyaSearch(query: Int) : AyaData

    suspend fun getHighligthData(question: String , context :String) :HighlightResponce
}