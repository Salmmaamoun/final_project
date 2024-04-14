package com.example.graduation_project.ui.Login
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.LoginUseCase


//val password_confirmation: List<String?>? = null,
    class ViewModelLoginFactory(private val useCase: LoginUseCase,val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LoginViewModel(useCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

}