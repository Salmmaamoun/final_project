package com.example.graduation_project.ui.bottomnavigationScreens.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentHomeBinding
import com.example.graduation_project.ui.base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }


    override val layoutFragmentId: Int
        get() = R.layout.fragment_home

    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")
}