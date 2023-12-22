package com.example.graduation_project.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.graduation_project.R
import com.example.graduation_project.databinding.ActivityOnBoardingScreenBinding
import com.example.graduation_project.ui.MainActivity
import com.example.weather_app.ui.base.BaseActivity
import com.example.weather_app.ui.base.onBoarding.FirstOnBoardingScreenFragment
import com.example.weather_app.ui.base.onBoarding.OnBoardingAdapter
import com.example.weather_app.ui.base.onBoarding.SecondOnBoardingScreenFragment
import com.example.weather_app.ui.base.onBoarding.ThirdOnBoardingScreenFragment

import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingScreenActivity() : BaseActivity<ActivityOnBoardingScreenBinding>(),
    Parcelable {
    lateinit var onboardingAdapter: OnBoardingAdapter
    override val layoutActivityId: Int = R.layout.activity_on_boarding_screen
    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        var onboardingScreens = listOf(
            ThirdOnBoardingScreenFragment(),
            SecondOnBoardingScreenFragment(),
            FirstOnBoardingScreenFragment()
        )

        onboardingAdapter = OnBoardingAdapter(supportFragmentManager,lifecycle ,onboardingScreens = onboardingScreens ,)
        binding .vpOnboarding.adapter = onboardingAdapter
        val inflater = LayoutInflater.from(this)

        TabLayoutMediator(binding.tlOnboardingDots, binding.vpOnboarding) { tab, position ->
            tab.view.background = ContextCompat.getDrawable(this, R.drawable.select_onboarding)
        }.attach()

        for (i in 0 until binding.tlOnboardingDots.tabCount) {
            val layoutParams = LinearLayout.LayoutParams(binding.tlOnboardingDots.getTabAt(i)?.view?.layoutParams)
            layoutParams.marginStart = 12
            layoutParams.marginEnd = 12
            binding.tlOnboardingDots.getTabAt(i)?.view?.layoutParams = layoutParams
        }

        //Implement skip button
       binding.btnSkip.setOnClickListener {
           val intent= Intent(it.context,MainActivity::class.java)
            startActivity(intent)
        }

        // Implement next button
        binding.btnNext.setOnClickListener {
           if (binding.vpOnboarding.currentItem == onboardingScreens.size - 1) {
            val intent= Intent(it.context, MainActivity::class.java)
                startActivity(intent)
            } else {
                binding.vpOnboarding.currentItem = binding.vpOnboarding.currentItem + 1
            }
        }


    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(layoutActivityId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OnBoardingScreenActivity> {
        override fun createFromParcel(parcel: Parcel): OnBoardingScreenActivity {
            return OnBoardingScreenActivity(parcel)
        }

        override fun newArray(size: Int): Array<OnBoardingScreenActivity?> {
            return arrayOfNulls(size)
        }
    }




}
