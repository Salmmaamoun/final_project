package com.example.graduation_project.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.graduation_project.R
import com.example.graduation_project.databinding.ActivityMainBinding
import com.example.graduation_project.ui.bottomnavigationScreens.HomeFragment
import com.example.graduation_project.ui.bottomnavigationScreens.QuaraanFragment
import com.example.graduation_project.ui.bottomnavigationScreens.TafseerFragment
import com.example.weather_app.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutActivityId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragments = listOf(
            HomeFragment(),
            QuaraanFragment(),
            TafseerFragment()
        )
        replaceFragment(fragments[0])

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


