package com.example.graduation_project.ui.bottomnavigationScreens.quran

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.graduation_project.R

import com.example.graduation_project.databinding.FragmentSoraListBinding
import com.example.graduation_project.ui.base.BaseFragment
import com.example.graduation_project.ui.bottomnavigationScreens.quran.QuraanContainer.QuranContainerFragment
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Sora


class SoraListFragment : BaseFragment<FragmentSoraListBinding>() , SoraListAdapter.SoraItemClickListener{
    private lateinit var listViewModel: ListViewModel
    override val layoutFragmentId: Int
        get() = R.layout.fragment_sora_list
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        binding.soraList.adapter=SoraListAdapter(listViewModel.getAllSoras(),this)
    }

    override fun onSoraItemClicked(sora: Sora) {
        val fragment = QuranContainerFragment()
        val bundle = Bundle().apply {
            putInt("startPage", sora.startPage)
        }
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.con, fragment)
            .commit()
    }



}