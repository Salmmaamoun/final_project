package com.example.graduation_project.ui.bottomnavigationScreens.home.lexical

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.data.remote.LoginRegiisterRetrofitInstance
import com.example.data.repo.datasource.DataSourceImp
import com.example.data.repo.repo.RepoImp
import com.example.domain.usecase.SearchLexicalUseCase
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentLexicalSearchBinding
import com.example.graduation_project.ui.bottomnavigationScreens.home.HomeFragment


class LexicalSearchFragment : Fragment() {

    private lateinit var binding: FragmentLexicalSearchBinding
    private val lexicalViewModel: LexicalSearchViewModel by viewModels {
        val apiService = LoginRegiisterRetrofitInstance.getApi()
        val aiApiService = LoginRegiisterRetrofitInstance.getAiApi()
        val apiSemanticService=LoginRegiisterRetrofitInstance.getApiSemSearch()
        val aiHighlightService=LoginRegiisterRetrofitInstance.getAiApiHighligth()
        val dataSource = apiService?.let { DataSourceImp(it ,aiApiService ,apiSemanticService , aiHighlightService)  }
        val repository = RepoImp(dataSource)
        val useCase = SearchLexicalUseCase(repository)
        SearchViewModelFactory(useCase)
    }
    private val adapter = LexicalResponseAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLexicalSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = lexicalViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recLexical.adapter = adapter
        binding.arrow.setOnClickListener {
            navigateToHome()
        }

        lexicalViewModel.lexicalResponse.observe(viewLifecycleOwner) { items ->
            items?.let {
                if (it.isNotEmpty()) {
                    adapter.updateItems(it)
                } else {
                    Toast.makeText(requireContext(), "No data available", Toast.LENGTH_SHORT).show()
                }
            }
        }
        lexicalViewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                // Show loading state
                showLoadingState()
            } else {
                // Hide loading state
                hideLoadingState()
            }
        })

        binding.btnSearch.setOnClickListener {
            val query = binding.searchAya.query.toString()
            lexicalViewModel.searchLexical(query)
        }
    }
    private fun showLoadingState() {
        // Disable buttons
        binding.searchAya.isEnabled = false
        binding.btnSearch.isEnabled = false
        binding.recLexical.isEnabled = false

        binding.loadingIndicator.visibility = View.VISIBLE
    }

    private fun hideLoadingState() {
        // Enable buttons
        binding.searchAya.isEnabled = true
        binding.btnSearch.isEnabled = true
        binding.recLexical.isEnabled = true
        binding.loadingIndicator.visibility = View.GONE
    }

    fun navigateToHome() {
        parentFragmentManager.beginTransaction().
        replace(R.id.frame_container, HomeFragment()).commit()
        }
}
