package com.example.graduation_project.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.graduation_project.R
import com.example.graduation_project.databinding.ActivitySignUpBinding
import com.example.weather_app.ui.base.BaseActivity

class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {
    override val layoutActivityId: Int
        get() = R.layout.activity_sign_up

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}