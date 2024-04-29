package com.example.graduation_project.ui.bottomnavigationScreens.home

import RegisterViewModel
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.data.remote.LoginRegiisterRetrofitInstance
import com.example.data.repo.repo.RepoImp
import com.example.domain.entity.RegisterRequest
import com.example.domain.usecase.SignUpUseCase
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentHomeBinding
import com.example.graduation_project.ui.base.BaseFragment
import com.example.graduation_project.ui.signup.ViewModelSignUpFactory


class HomeFragment : BaseFragment<FragmentHomeBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


}



    override val layoutFragmentId: Int
        get() = R.layout.fragment_home

    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")
}