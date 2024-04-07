package com.example.graduation_project.ui.Login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.data.remote.ApiService
import com.example.data.remote.LoginRegiisterRetrofitInstance
import com.example.data.repo.datasource.DataSourceImp
import com.example.data.repo.repo.RepoImp
import com.example.domain.entity.LoginResponse
import com.example.domain.usecase.LoginUseCase
import com.example.domain.usecase.SignUpUseCase
import com.example.graduation_project.R
import com.example.graduation_project.databinding.ActivityLoginBinding
import com.example.graduation_project.ui.MainActivity
import com.example.graduation_project.ui.signup.SignUpActivity
import com.example.graduation_project.ui.signup.ViewModelFactory
import com.example.weather_app.ui.base.BaseActivity
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private lateinit var viewModel: LoginViewModel
    override val layoutActivityId: Int
        get() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val apiService = LoginRegiisterRetrofitInstance.getApi()
        val repository = RepoImp(apiService)
        val useCase = LoginUseCase(repository)
        val viewModelFactory = ViewModelFactory(useCase)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        setUpViews()
        observeRegistrationResult()
        observeValidationErrors()

    }

    private fun observeRegistrationResult() {
        viewModel.loginResult.observe(this, Observer { response ->
            if (response != null) {
                if (response.statusCode != 422) {
                    Log.d("response", "Successful registration")
                } else {
                    Log.d("response", "Error during registration")
                }
            } else {
                Log.d("response", "Null response received")
            }
        })
    }

    private fun observeValidationErrors() {
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
    }

    private fun setUpViews() {

        binding.checkBoxTerms.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

                binding.editTextPassword.transformationMethod = null
            } else {
                binding.editTextPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }
        }

        binding.textCreatAccout.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.buttonLogin.setOnClickListener {

            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()



            if (!(email.isEmpty() && password.isEmpty())) {
                viewModel.loginUser(email, password)
                Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }

        }
    }


}