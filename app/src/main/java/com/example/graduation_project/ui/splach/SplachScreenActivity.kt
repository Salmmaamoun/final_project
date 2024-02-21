package com.example.graduation_project.ui.splach

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.graduation_project.R
import com.example.graduation_project.databinding.ActivitySplachScreenBinding

import com.example.graduation_project.ui.onboarding.OnBoardingScreenActivity

class SplachScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySplachScreenBinding= DataBindingUtil.setContentView(this, R.layout.activity_splach_screen)
        val splashScreenDuration = 4000L // In milliseconds
        Thread(Runnable {
            Thread.sleep(splashScreenDuration)
            // Start the main activity after the splash screen duration
            startActivity(Intent(this, OnBoardingScreenActivity::class.java))
            finish() // Close the splash screen activity
        }).start()

    }
}