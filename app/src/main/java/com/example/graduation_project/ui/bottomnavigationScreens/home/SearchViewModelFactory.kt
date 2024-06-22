package com.example.graduation_project.ui.bottomnavigationScreens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.SearchLexicalUseCase

class SearchViewModelFactory(private val searchLexicalUseCase: SearchLexicalUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchLexicalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchLexicalViewModel(searchLexicalUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
