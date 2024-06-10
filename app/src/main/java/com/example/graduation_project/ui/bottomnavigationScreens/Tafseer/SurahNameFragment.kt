package com.example.graduation_project.ui.bottomnavigationScreens.Tafseer

import SurahAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.remote.LoginRegiisterRetrofitInstance
import com.example.data.repo.datasource.DataSourceImp
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
        val dataSource= apiService?.let { DataSourceImp(it) }
        val repository = RepoImp(dataSource)
        val useCase = SurahNameUseCase(repository)
        val viewModelFactory = ViewModelFactory(useCase)

        suraViewModel = ViewModelProvider(this, viewModelFactory).get(SurahNameViewModel::class.java)

          adapter=SurahAdapter(this)

        binding.soraList.adapter = adapter
        suraViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                // Show loading state
                showLoadingState()
            } else {
                // Hide loading state
                hideLoadingState()
            }
        })

        suraViewModel.dataItems.observe(viewLifecycleOwner) { dataItems ->
            dataItems?.let {
                adapter.setData(it)
            }


        }

        // Call method to fetch data
        suraViewModel.fetchData("ar")
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
    private fun showLoadingState() {
        // Disable buttons or interactions if necessary
        binding.soraList.isEnabled = false

        binding.loading.visibility = View.VISIBLE
    }

    private fun hideLoadingState() {
        // Enable buttons or interactions if necessary
        binding.soraList.isEnabled = true

        binding.loading.visibility = View.GONE
    }
}