import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.RegisterRequest
import com.example.domain.entity.RegisterResponse
import com.example.domain.usecase.SignUpUseCase
import com.google.gson.Gson

import kotlinx.coroutines.launch
import retrofit2.HttpException



class RegisterViewModel(private val useCase: SignUpUseCase,private val context: Context) : ViewModel() {

    private val _registrationResult = MutableLiveData<RegisterResponse>()
    val registrationResult: LiveData<RegisterResponse> = _registrationResult

    // LiveData for validation errors
    private val _nameError = MutableLiveData<String?>()
    val nameError: LiveData<String?> = _nameError

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    private val _passwordConfirmationError = MutableLiveData<String?>()
    val passwordConfirmationError: LiveData<String?> = _passwordConfirmationError

    private val _phoneError = MutableLiveData<String?>()
    val phoneError: LiveData<String?> = _phoneError

    private val _selectedImageUri = MutableLiveData<Uri?>()
    val selectedImageUri: LiveData<Uri?> = _selectedImageUri
    fun registerUser(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String,
        gender: String,
        phone: String,
        image: Uri?
    ) {
        if (!validateInputs(name, email, password, passwordConfirmation, phone)) {
            // Validation failed
            return
        }

        // Proceed with registration
        val request = RegisterRequest(name, email, password, gender, phone,_selectedImageUri.value?.let { uri ->    convertUriToByteArray(uri)
        })
        viewModelScope.launch {
            try {
                val response = useCase.invoke(request)
                _registrationResult.postValue(response)
                Log.d("response", "Registration successful")
            } catch (e: HttpException) {
                // Handle HTTP errors
                val errorBody = e.response()?.errorBody()?.string()
                val registerResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
                handleRegistrationError(registerResponse)
            } catch (e: Exception) {
                // Handle other errors
                Log.e("error", "Error registering user", e)
            }
        }
    }

    // Validation logic
    private fun validateInputs(name: String, email: String, password: String, passwordConfirmation: String, phone: String): Boolean {
        // Validate inputs and set error messages accordingly
        return true // Return true if inputs are valid, false otherwise
    }

    // Function to handle registration errors
    private fun handleRegistrationError(response: RegisterResponse) {
        response.errors?.let { errors ->
            _nameError.value = errors?.name?.getOrNull(0)
            _emailError.value = errors.email?.getOrNull(0)
            _passwordError.value = errors.password?.getOrNull(0)
            _phoneError.value = errors.phone?.getOrNull(0)
        }
    }
    private fun convertUriToByteArray(uri: Uri): ByteArray? {
        val inputStream = context.contentResolver.openInputStream(uri)
        return inputStream?.readBytes()
    }
    fun selectImageUri(uri: Uri?) {
        _selectedImageUri.value = uri
    }
}