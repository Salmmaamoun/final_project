package com.example.graduation_project.ui.bottomnavigationScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentTafseerBinding
import com.example.graduation_project.ui.base.BaseFragment


class TafseerFragment :BaseFragment<FragmentTafseerBinding>(){
    override val layoutFragmentId: Int
        get() = R.layout.fragment_tafseer
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


}