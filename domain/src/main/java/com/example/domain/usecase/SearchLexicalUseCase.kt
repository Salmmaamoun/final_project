package com.example.domain.usecase

import com.example.domain.abstraction.Repo.Repo
import com.example.domain.entity.LexicalResponse
import com.example.domain.entity.LexicalResponseItem


class SearchLexicalUseCase(private val repo: Repo) {
    suspend fun searchLexical(query: String): List<LexicalResponseItem> {
        return repo.getSearchLexical(query)
    }
}

