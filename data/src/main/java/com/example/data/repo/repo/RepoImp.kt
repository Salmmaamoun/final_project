package com.example.data.repo.repo

import com.example.data.remote.ApiService
import com.example.domain.abstraction.Repo.Repo
import com.example.domain.entity.LoginRequest
import com.example.domain.entity.RegisterRequest
import com.example.domain.entity.LoginResponse
import com.example.domain.entity.RegisterResponse
import com.example.domain.entity.SurahResponse
import com.example.domain.entity.TafseerResponse

class RepoImp(val dataSource: ApiService?):Repo {
    override suspend fun login(request: LoginRequest): LoginResponse {
        return dataSource!!.login(request)
    }

    override suspend fun register(
        request: RegisterRequest
    ): RegisterResponse {
        return dataSource!!.register(request)
    }

    override suspend fun getSurahs(language: String): SurahResponse {
        return dataSource!!.getSurahs(language)
    }

    override suspend fun getAyah(
        editionId: Int,
        surahNumber: Int,
        ayahNumber: Int
    ): TafseerResponse {
        return dataSource!!.getAyah(editionId,surahNumber,ayahNumber)
    }


}