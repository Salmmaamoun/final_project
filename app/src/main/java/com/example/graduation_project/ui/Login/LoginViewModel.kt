package com.example.graduation_project.ui.Login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.LoginRequest
import com.example.domain.entity.LoginResponse
import com.example.domain.entity.RegisterRequest
import com.example.domain.entity.RegisterResponse
import com.example.domain.usecase.LoginUseCase
import com.example.domain.usecase.SignUpUseCase
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val useCase:LoginUseCase) :ViewModel(){
    private val _loginResult = MutableLiveData<LoginResponse>()
    val loginResult: LiveData<LoginResponse> = _loginResult

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError
    fun loginUser( email: String, password: String) {
        if (!validateInputs( email, password)) {
            // Validation failed
            return
        }

        // Proceed with registration
        val request = LoginRequest(email, password)
        viewModelScope.launch {
            try {
                val response = useCase.invoke(request)
                _loginResult.postValue(response)
                Log.d("response", "Registration successful")
            } catch (e: HttpException) {
                // Handle HTTP errors
                val errorBody = e.response()?.errorBody()?.string()
                val loginResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
                handleRegistrationError(loginResponse)
            } catch (e: Exception) {
                // Handle other errors
                Log.e("error", "Error registering user", e)
            }
        }
    }
    private fun validateInputs( email: String, password: String): Boolean {
        // Validate inputs and set error messages accordingly
        return true // Return true if inputs are valid, false otherwise
    }

    // Function to handle registration errors
    private fun handleRegistrationError(response: LoginResponse) {
        response.errors?.let { errors ->
            _emailError.value = errors.email?.getOrNull(0)
            _passwordError.value = errors.password?.getOrNull(0)

        }
    }

}
