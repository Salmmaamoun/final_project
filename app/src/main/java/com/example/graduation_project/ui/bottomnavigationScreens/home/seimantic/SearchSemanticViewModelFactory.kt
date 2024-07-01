package com.example.graduation_project.ui.bottomnavigationScreens.home.seimantic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.SearchSemanticUseCase

class SearchSemanticViewModelFactory (private val searchSemanticUseCase: SearchSemanticUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SemanticSearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SemanticSearchViewModel(searchSemanticUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
