package com.example.graduation_project.ui.bottomnavigationScreens.Tafseer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.AyaUseCase

class TaseerViewModelFactory(private val useCase: AyaUseCase) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TafseerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TafseerViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}