package com.example.graduation_project.ui.bottomnavigationScreens.quran


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentQuntainerBinding
import com.example.graduation_project.databinding.FragmentQuranContainerBinding
import com.example.graduation_project.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class QuranContainerFragment : BaseFragment<FragmentQuntainerBinding>() {

    override val layoutFragmentId: Int
        get() = R.layout.fragment_quntainer

    override val viewModel: ViewModel
        get() = TODO()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPagerAdapter = ViewPagerAdapter(requireActivity())
        binding.quranHome.adapter = viewPagerAdapter

        TabLayoutMediator(binding.fragQuranContainerTablayout, binding.quranHome) { tab, position ->
            tab.text = when (position) {
                0 -> "Sora List"
                1 -> "Juzz List"
                else -> null
            }
        }.attach()
    }

    private inner class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> SoraListFragment()
                1 -> JuzzFragment()
                else -> throw IllegalStateException("Unexpected position $position")
            }
        }
    }
}
