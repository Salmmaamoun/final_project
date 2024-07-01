package com.example.graduation_project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.domain.entity.LoginRequest
import com.example.graduation_project.R
import com.example.graduation_project.databinding.ActivityMainBinding
import com.example.graduation_project.ui.bottomnavigationScreens.home.HomeFragment
import com.example.graduation_project.ui.bottomnavigationScreens.Tafseer.TafseerFragment
import com.example.weather_app.ui.base.BaseActivity
import com.example.graduation_project.ui.bottomnavigationScreens.quran.ContainerFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutActivityId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val req=LoginRequest("ss","sss")

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_quran -> {
                    replaceFragment(ContainerFragment())
                    true
                }
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.navigation_qura -> {
                    replaceFragment(TafseerFragment())
                    true
                }
                else -> false
            }
        }
// Set default selected item
        binding.bottomNavigation.selectedItemId = R.id.navigation_home
    }
    private fun replaceFragment(fragment:Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }
}