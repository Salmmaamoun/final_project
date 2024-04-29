package com.example.domain.usecase

import com.example.domain.abstraction.Repo.Repo
import com.example.domain.entity.LexicalSearchResponse

class SearchLexicalUseCase(val repo :Repo) {//rename
    suspend operator fun invoke(term :String): LexicalSearchResponse {
        return repo.getSearchLexical(term)
    }

}
