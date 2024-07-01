package com.example.domain.usecase

import com.example.domain.abstraction.Repo.Repo
import com.example.domain.entity.AyaData
import com.example.domain.entity.HighlightResponce
import com.example.domain.entity.LexicalResponseItem
import com.example.domain.entity.SemanticAiResponce
import com.example.domain.entity.SemanticApiResponse
import com.example.domain.entity.TafseerResponse

class SearchSemanticUseCase(val repo: Repo) {
    suspend fun getSemanticsearch(query: String): SemanticAiResponce{
        return repo.getSemanticsearch(query)
    }

    suspend fun excuteApiSemantic(id : Int) : AyaData{
        return repo.getSmanticAyaSearch(id)
    }

    suspend fun executeHighligth(question: String , context:String): HighlightResponce? {
        return repo.getHighligthData(question ,context)
    }

}