package com.example.graduation_project.ui.splach

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import com.example.graduation_project.R
import com.example.graduation_project.databinding.ActivitySplachScreenBinding

import com.example.graduation_project.ui.onboarding.OnBoardingScreenActivity
import com.example.weather_app.ui.base.BaseActivity

class SplachScreenActivity : BaseActivity<ActivitySplachScreenBinding>() {
    override val layoutActivityId: Int
        get() = R.layout.activity_splach_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreenDuration = 5000L // In milliseconds
        Thread(Runnable {
            Thread.sleep(splashScreenDuration)
            // Start the main activity after the splash screen duration
            startActivity(Intent(this, OnBoardingScreenActivity::class.java))
            finish() // Close the splash screen activity
        }).start()
        }
}