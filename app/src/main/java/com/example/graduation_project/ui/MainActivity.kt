package com.example.graduation_project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment

import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.domain.entity.LoginRequest
import com.example.graduation_project.R
import com.example.graduation_project.databinding.ActivityMainBinding
import com.example.graduation_project.ui.bottomnavigationScreens.HomeFragment
import com.example.graduation_project.ui.bottomnavigationScreens.quran.SoraListFragment
import com.example.graduation_project.ui.bottomnavigationScreens.Tafseer.TafseerFragment
import com.example.weather_app.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutActivityId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val req=LoginRequest("ss","sss")

        val fragments = listOf(
            SoraListFragment(),
            HomeFragment(),
            TafseerFragment()
        )


        // Configure the Meow Bottom Navigation
        binding.bottomNavigation.apply {
            add(MeowBottomNavigation.Model(1, R.drawable.quran))
            add(MeowBottomNavigation.Model(2, R.drawable.hom))
            add(MeowBottomNavigation.Model(3, R.drawable.qura))

            setOnClickMenuListener {
                val fragment = fragments[it.id - 1]
                replaceFragment(fragment)
            }

            show(2)
        }
    }

    private fun replaceFragment(fragment:Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }
}


