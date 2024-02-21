package com.example.graduation_project.ui.bottomnavigationScreens.Tafseer
import RegisterViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.SignUpUseCase
import com.example.domain.usecase.SurahNameUseCase


//val password_confirmation: List<String?>? = null,
    class ViewModelFactory(private val useCase: SurahNameUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SurahNameViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SurahNameViewModel(useCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

}