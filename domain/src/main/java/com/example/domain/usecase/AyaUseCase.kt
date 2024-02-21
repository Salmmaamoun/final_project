package com.example.domain.usecase

import com.example.domain.abstraction.Repo.Repo
import com.example.domain.entity.TafseerResponse

class AyaUseCase(val repo: Repo) {
    suspend operator fun invoke(editionId: Int, surahNumber: Int, ayahNumber: Int): TafseerResponse {
        return repo.getAyah(editionId,surahNumber,ayahNumber)
    }

}