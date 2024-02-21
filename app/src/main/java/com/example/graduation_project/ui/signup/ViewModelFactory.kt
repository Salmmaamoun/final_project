package com.example.graduation_project.ui.signup
import RegisterViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.SignUpUseCase


//val password_confirmation: List<String?>? = null,
    class ViewModelFactory(private val useCase: SignUpUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return RegisterViewModel(useCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

}