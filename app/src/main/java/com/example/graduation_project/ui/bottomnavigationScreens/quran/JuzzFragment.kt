package com.example.graduation_project.ui.bottomnavigationScreens.quran

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentJuzzBinding
import com.example.graduation_project.ui.base.BaseFragment
import com.example.graduation_project.ui.bottomnavigationScreens.quran.QuraanContainer.QuranContainerFragment
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Juzz
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Sora


class JuzzFragment : BaseFragment<FragmentJuzzBinding>(),JuzzAdapter.SoraItemClickListener {
    private lateinit var adapter: JuzzAdapter
    private lateinit var listViewModel: JuzzViewModel
    override val layoutFragmentId: Int
        get() = R.layout.fragment_juzz
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listViewModel = ViewModelProvider(this).get(JuzzViewModel::class.java)
        binding.soraList.adapter=JuzzAdapter(listViewModel.getAllData(),this)
    }

    override fun onSoraItemClicked(sora:Juzz) {
        val fragment = QuranContainerFragment()
        val bundle = Bundle().apply {
            putInt("startPage", sora.startPage)
        }
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }


    }


