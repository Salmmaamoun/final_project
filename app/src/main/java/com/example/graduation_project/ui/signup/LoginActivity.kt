package com.example.graduation_project.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.graduation_project.R
import com.example.graduation_project.databinding.ActivityLoginBinding
import com.example.weather_app.ui.base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override val layoutActivityId: Int
        get() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}