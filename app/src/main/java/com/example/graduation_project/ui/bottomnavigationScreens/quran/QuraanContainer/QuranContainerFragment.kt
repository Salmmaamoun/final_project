package com.example.graduation_project.ui.bottomnavigationScreens.quran.QuraanContainer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentQuranContainerBinding
import com.example.graduation_project.ui.base.BaseFragment


class QuranContainerFragment : BaseFragment<FragmentQuranContainerBinding>() {
    private lateinit var viewPager: ViewPager2
    private var startPage: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        startPage = args?.getInt("startPage", 1) ?: 1

    }

    override val layoutFragmentId: Int
        get() = R.layout.fragment_quran_container
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = QuraanPageAdapter(this)
        binding.quranPager.adapter = pagerAdapter
        binding.quranPager.setCurrentItem(604 - startPage, false)

    }
    /////////////



}