package com.example.graduation_project.ui.bottomnavigationScreens.home

import RegisterViewModel
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentHomeBinding
import com.example.graduation_project.ui.base.BaseFragment
import com.squareup.picasso.Picasso


class HomeFragment :BaseFragment<FragmentHomeBinding>(){
  lateinit var RegisterViewModel: RegisterViewModel
  private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Load and display the saved image URI
        val imageUri = sharedPreferences.getString("imageUri", "")
        if (!imageUri.isNullOrEmpty()) {
            binding.mm.setImageURI(Uri.parse(imageUri))
        }

    }

    override val layoutFragmentId: Int
        get() = R.layout.fragment_home
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")




}