package com.example.graduation_project.ui.bottomnavigationScreens.home.seimantic

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.remote.LoginRegiisterRetrofitInstance
import com.example.data.repo.datasource.DataSourceImp
import com.example.data.repo.repo.RepoImp
import com.example.domain.entity.AyaData
import com.example.domain.usecase.SearchSemanticUseCase
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentSeimanticBinding
import com.example.graduation_project.ui.bottomnavigationScreens.home.HomeFragment

class SeimanticFragment : Fragment() {

    private val semanticViewModel: SemanticSearchViewModel by viewModels {
        val apiService = LoginRegiisterRetrofitInstance.getApi()
        val aiApiService = LoginRegiisterRetrofitInstance.getAiApi()
        val apiSemanticService = LoginRegiisterRetrofitInstance.getApiSemSearch()
        val aiHighlightService = LoginRegiisterRetrofitInstance.getAiApiHighligth()
        val dataSource = DataSourceImp(apiService, aiApiService, apiSemanticService, aiHighlightService)
        val repository = RepoImp(dataSource)
        val useCase = SearchSemanticUseCase(repository)
        SearchSemanticViewModelFactory(useCase)
    }

    private lateinit var binding: FragmentSeimanticBinding
    private lateinit var adapter: Adapter
    private lateinit var question :String
    private lateinit var ayaDataList :List<AyaData>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeimanticBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.arrow.setOnClickListener {
            navigateToHome()
        }

        setupAdapter()

        semanticViewModel.semanticResponce.observe(viewLifecycleOwner) { items ->
            items?.let {
                if (it.isNotEmpty()) {
                    ayaDataList=it
                    adapter.submitList(it)
                } else {
                    Toast.makeText(requireContext(), "No data available", Toast.LENGTH_SHORT).show()
                }
            }
        }

        semanticViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                showLoadingState()
            } else {
                hideLoadingState()
            }
        }

        binding.btnSearch.setOnClickListener {
            val query = binding.searchAya.query.toString()
            semanticViewModel.getSemanticSearchResponce(query)
            adapter.question = query
            question=query
        }
    }

    private fun showLoadingState() {
        binding.searchAya.isEnabled = false
        binding.btnSearch.isEnabled = false
        binding.recLexical.isEnabled = false
        binding.loadingIndicator.visibility = View.VISIBLE
    }

    private fun hideLoadingState() {
        binding.searchAya.isEnabled = true
        binding.btnSearch.isEnabled = true
        binding.recLexical.isEnabled = true
        binding.loadingIndicator.visibility = View.GONE
    }

    private fun setupAdapter() {
        val onHighligthListner ={ayadata :AyaData , position :Int ->
            semanticViewModel.getHighligth(question ,ayadata.verseWithoutTashkeel.toString())
            semanticViewModel.highligthResponse.observe(viewLifecycleOwner){
                Log.d("LifeData in fragment" ,it.toString())
                ayaDataList.forEach{
                    it.isHighligthed=false
                }
                ayaDataList[position].isHighligthed=true
                ayaDataList[position].highligthedResponse=it
                adapter.submitList(ayaDataList)
                adapter.notifyDataSetChanged()
            }
        }
        adapter = Adapter(onHighligthListner)
        binding.apply {
            recLexical.adapter = adapter
            recLexical.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    fun navigateToHome() {
        parentFragmentManager.beginTransaction().
        replace(R.id.frame_container, HomeFragment()).commit()
    }
}