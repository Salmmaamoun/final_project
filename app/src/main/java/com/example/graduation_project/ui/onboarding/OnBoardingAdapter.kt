package com.example.weather_app.ui.base.onBoarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardingAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val onboardingScreens: List<Fragment>
) : FragmentStateAdapter
    (fragmentManager, lifecycle) {
    override fun getItemCount(): Int =onboardingScreens.size

    override fun createFragment(position: Int): Fragment {
       return onboardingScreens[position]
    }

}