package com.example.domain.usecase

import com.example.domain.abstraction.Repo.Repo
import com.example.domain.entity.DataItem
import com.example.domain.entity.RegisterRequest
import com.example.domain.entity.RegisterResponse
import com.example.domain.entity.SurahResponse

class SurahNameUseCase(val repo: Repo) {
    suspend operator fun invoke(language: String): SurahResponse {
        return repo.getSurahs(language)
    }
}