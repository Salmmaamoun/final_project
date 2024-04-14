package com.example.graduation_project.ui.signup
import RegisterViewModel
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.data.remote.LoginRegiisterRetrofitInstance
import com.example.data.repo.repo.RepoImp
import com.example.domain.usecase.SignUpUseCase
import com.example.graduation_project.R
import com.example.graduation_project.databinding.ActivitySignUpBinding
import com.example.graduation_project.ui.Login.LoginActivity

import com.example.weather_app.ui.base.BaseActivity

private const val TAG = "SignUpActivity"
class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {
    private lateinit var viewModel: RegisterViewModel
    private val CAMERA_PERMISSION_REQUEST_CODE = 1001

    override val layoutActivityId: Int
        get() = R.layout.activity_sign_up

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            try {
                if (result.resultCode == Activity.RESULT_OK) {
                    val imageUri = result.data?.data
                    imageUri?.let {
                        viewModel.selectImageUri(it)
                        binding.imageViewProfile.setImageURI(it)
                    }
                } else {
                    Toast.makeText(this, "Image selection canceled", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("img3", "Error picking image", e)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = ViewModelSignUpFactory(getSignUpUseCase(),this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)
        setUpViews()
        observeRegistrationResult()
        observeValidationErrors()
    }

    private fun getSignUpUseCase(): SignUpUseCase {
        val apiService = LoginRegiisterRetrofitInstance.getApi()
        val repository = RepoImp(apiService)
        return SignUpUseCase(repository)
    }

    private fun observeRegistrationResult() {
        viewModel.registrationResult.observe(this, Observer { response ->
            if (response != null) {
                if (response.statusCode != 422) {
                    Log.d("ss", "Successful registration")
                } else {
                    Log.d("ss", "Error during registration")
                }
            } else {
                Log.d("ss", "Null response received")
            }
        })
    }

    private fun observeValidationErrors() {
        viewModel.nameError.observe(this, Observer { error ->
            error?.let { binding.editTextUsername.error = it }
        })

        viewModel.emailError.observe(this, Observer { error ->
            error?.let { binding.editTextEmail.error = it }
        })

        viewModel.passwordError.observe(this, Observer { error ->
            error?.let { binding.editTextPassword.error = it }
        })

        viewModel.phoneError.observe(this, Observer { error ->
            error?.let { binding.editTextPhone.error = it }
        })
    }

    private fun setUpViews() {
        binding.imageViewProfile.setOnClickListener {
            Log.d("img3", "ImageView clicked")
            checkPermissionsAndPickImage()
        }

        binding.buttonSignUp.setOnClickListener {
            val name = binding.editTextUsername.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val passwordConfirmation = binding.editTextPasswordconfirm.text.toString()
            Log.d("passcon",passwordConfirmation)
            Log.d("pass",password)
            val gender = if (binding.radioButtonMale.isChecked) "male" else "female"
            val phone = binding.editTextPhone.text.toString()
            val image = viewModel.selectedImageUri.value


           viewModel.registerUser(name, email, password, passwordConfirmation, gender, phone, image)

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
                && passwordConfirmation.isNotEmpty() && phone.isNotEmpty() && image != null
            ) {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                startActivity(intent)
            }
        }

        binding.textViewSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkPermissionsAndPickImage() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        } else {
            pickImage()
        }
    }

    private fun pickImage() {
        val pickImageIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImageLauncher.launch(pickImageIntent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImage()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
