package com.example.data.repo.datasource


import com.example.data.remote.ApiService
import com.example.domain.abstraction.DataSourceRepo.DataSourceRepo
import com.example.domain.entity.LoginRequest
import com.example.domain.entity.RegisterRequest
import com.example.domain.entity.LoginResponse
import com.example.domain.entity.RegisterResponse
import com.example.domain.entity.SurahResponse
import com.example.domain.entity.TafseerResponse

class DataSourceImp(val apiSevice: ApiService): DataSourceRepo {
    override suspend fun login(request: LoginRequest): LoginResponse {

        return apiSevice.login(request)
    }

    override suspend fun register(request:RegisterRequest
    ): RegisterResponse {
        return apiSevice.register(request)
    }

    override suspend fun getSurahs(language: String):SurahResponse {
        return apiSevice.getSurahs(language)
    }

    override suspend fun getAyah(
        editionId: Int,
        surahNumber: Int,
        ayahNumber: Int
    ): TafseerResponse {
        return apiSevice.getAyah(editionId,surahNumber,ayahNumber)
    }

}