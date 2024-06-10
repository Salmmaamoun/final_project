package com.example.graduation_project.ui.bottomnavigationScreens.quran.QuraanContainer


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.graduation_project.ui.bottomnavigationScreens.quran.quranPage.QuraanPageFragment

class QuraanPageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val NUM_PAGES = 604
    override fun getItemCount(): Int = NUM_PAGES
    override fun createFragment(position: Int): Fragment = QuraanPageFragment((NUM_PAGES-position))
}
