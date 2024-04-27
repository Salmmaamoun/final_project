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


private const val TAG = "RegisterViewModel"
class RegisterViewModel(private val useCase: SignUpUseCase, private val context: Context) : ViewModel() {
    private val _registrationResult = MutableLiveData<RegisterResponse>()
    val registrationResult: LiveData<RegisterResponse> = _registrationResult

    private val _nameError = MutableLiveData<String?>()
    val nameError: LiveData<String?> = _nameError

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

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
            return
        }

        val request = RegisterRequest(
            name, email, password,passwordConfirmation, gender, phone,
            image?.let { uri -> convertUriToByteArray(uri) }
        )
        Log.d(TAG, "registerUser: $request")

        viewModelScope.launch {
            try {
                val response = useCase.invoke(request)
                _registrationResult.postValue(response)
                Log.d("response", "Registration successful $response")
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val registerResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
                handleRegistrationError(registerResponse)
            } catch (e: Exception) {
                Log.e("error", "Error registering user", e)
                Log.e("error", "Exception class: ${e.javaClass.simpleName}")
                Log.e("error", "Exception message: ${e.message}")
            }
        }
    }

    private fun validateInputs(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String,
        phone: String
    ): Boolean {

        return true
    }

    private fun handleRegistrationError(response: RegisterResponse) {
        response.errors?.let { errors ->
            _nameError.value = errors?.name?.getOrNull(0)
            _emailError.value = errors.email?.getOrNull(0)
            _passwordError.value = if (errors.password?.contains("confirmation") == true) {
                "The password confirmation does not match."
            } else {
                errors.password?.getOrNull(0)
            }
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
    fun saveUserDataToPrefs(request: RegisterRequest) {
        val prefs = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
        prefs.edit().putString("name", request.name).apply()
        prefs.edit().putString("email", request.email).apply()
        prefs.edit().putString("password", request.password).apply()
        prefs.edit().putString("password_confirmation", request.password_confirmation).apply()
        prefs.edit().putString("phone", request.phone).apply()
        prefs.edit().putString("gender", request.gender).apply()
        prefs.edit().putString("image", request.image?.toString()).apply()
        Log.d("prefs", request.toString())
    }
}