package com.example.weather_app.ui.base.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentSecondOnBoardingScreenBinding
import com.example.graduation_project.ui.base.BaseFragment

class SecondOnBoardingScreenFragment : BaseFragment<FragmentSecondOnBoardingScreenBinding>() {
    override val layoutFragmentId: Int= R.layout.fragment_second_on_boarding_screen
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}