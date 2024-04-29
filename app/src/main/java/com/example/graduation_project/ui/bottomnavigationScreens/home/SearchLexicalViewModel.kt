package com.example.graduation_project.ui.bottomnavigationScreens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Aya
import com.example.domain.usecase.SearchLexicalUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchLexicalViewModel(private val searchLexicalUseCase: SearchLexicalUseCase) : ViewModel() {

    private val _searchResults = MutableStateFlow<List<Aya>>(emptyList())
    val searchResults: StateFlow<List<Aya>> = _searchResults

    fun searchLexical(term: String) {
        viewModelScope.launch {
            val response = searchLexicalUseCase(term)
            _searchResults.value = response.data?.flatMap { it.ayahs } ?: emptyList()
            Log.d("ds",response.data.toString())
        }
    }
}