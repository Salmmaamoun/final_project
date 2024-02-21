package com.example.graduation_project.ui.Login
import RegisterViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.LoginUseCase
import com.example.domain.usecase.SignUpUseCase


//val password_confirmation: List<String?>? = null,
    class ViewModelFactory(private val useCase:LoginUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LoginViewModel(useCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

}