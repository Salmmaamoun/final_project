package com.example.graduation_project.ui.signup

import RegisterViewModel
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.data.remote.LoginRegiisterRetrofitInstance
import com.example.data.repo.repo.RepoImp
import com.example.domain.usecase.SignUpUseCase
import com.example.graduation_project.R
import com.example.graduation_project.databinding.ActivitySignUpBinding
import com.example.graduation_project.ui.Login.LoginActivity
import com.example.graduation_project.ui.MainActivity
import com.example.weather_app.ui.base.BaseActivity

class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {

    private lateinit var viewModel: RegisterViewModel

    override val layoutActivityId: Int
        get() = R.layout.activity_sign_up

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = LoginRegiisterRetrofitInstance.getApi()
        val repository = RepoImp(apiService)
        val useCase = SignUpUseCase(repository)
        val viewModelFactory = ViewModelFactory(useCase)

        viewModel = ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)

        setUpViews()
        observeRegistrationResult()
        observeValidationErrors()
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
            error?.let {

                binding.editTextUsername.error = it
            }
        })

        viewModel.emailError.observe(this, Observer { error ->
            error?.let {

                binding.editTextEmail.error = it
            }
        })

        viewModel.passwordError.observe(this, Observer { error ->
            error?.let {

                binding.editTextPassword.error = it
            }
        })



        viewModel.phoneError.observe(this, Observer { error ->
            error?.let {
                binding.editTextPhone.error = it
            }
        })
    }

    private fun setUpViews() {
      binding.textInputLayoutPassword.setEndIconOnClickListener {
            // Toggle the input type of the EditText between textPassword and textVisiblePassword
            val currentInputType = binding.editTextPassword.inputType
            if (currentInputType == android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                // Hide the password text
                binding.editTextPassword.inputType =
                    android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            } else {
                // Show the password text
                binding.editTextPassword.inputType =
                    android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }
        }

        binding.buttonSignUp.setOnClickListener {
            val name = binding.editTextUsername.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val passwordConfirmation = binding.editTextPasswordconfirm.text.toString()
            val gender = if (binding.radioButtonMale.isChecked) "male" else "female"
            val phone = binding.editTextPhone.text.toString()

            // Call the registerUser function with the input values
            viewModel.registerUser(name, email, password, passwordConfirmation, gender, phone)


           if(!(email.isEmpty()&&password.isEmpty()&&name.isEmpty()&&passwordConfirmation.isEmpty()&&name.isEmpty()&&phone.isEmpty())){
                Toast.makeText(this,"Registration successful",Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                startActivity(intent)

            }



        }
        binding.textViewSignIn.setOnClickListener {
            val intent = Intent(this,   LoginActivity::class.java)
            startActivity(intent)
        }


    }
}