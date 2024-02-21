package com.example.graduation_project.ui.bottomnavigationScreens.Tafseer

import SurahAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.remote.LoginRegiisterRetrofitInstance
import com.example.data.repo.repo.RepoImp
import com.example.domain.entity.DataItem
import com.example.domain.usecase.SurahNameUseCase
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentSurahNameBinding
import com.example.graduation_project.ui.base.BaseFragment


class SurahNameFragment : BaseFragment <FragmentSurahNameBinding>(),
    SurahAdapter.SurahItemClickListener {
    private lateinit var suraViewModel:SurahNameViewModel
    private lateinit var adapter:SurahAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override val layoutFragmentId: Int
        get() = R.layout.fragment_surah_name
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apiService = LoginRegiisterRetrofitInstance.getApi()
        val repository = RepoImp(apiService)
        val useCase = SurahNameUseCase(repository)
        val viewModelFactory = ViewModelFactory(useCase)

        suraViewModel = ViewModelProvider(this, viewModelFactory).get(SurahNameViewModel::class.java)

          adapter=SurahAdapter(this)

        binding.soraList.adapter = adapter

        suraViewModel.dataItems.observe(viewLifecycleOwner) { dataItems ->
            dataItems?.let {
                adapter.setData(it)
            }
        }

        // Call method to fetch data
        suraViewModel.fetchData("en")
}

    override fun onSurahItemClick(dataItem: DataItem) {
        val fragment=TafseerFragment()
        val bundle=Bundle().apply {
            putString("dataItem1",dataItem.name)
            putString("dataItem2",dataItem.id.toString())
        }
        fragment.arguments=bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }
}