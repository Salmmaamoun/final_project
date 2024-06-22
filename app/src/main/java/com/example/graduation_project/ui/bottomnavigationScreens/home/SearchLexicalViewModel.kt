package com.example.graduation_project.ui.bottomnavigationScreens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Aya
import com.example.domain.usecase.SearchLexicalUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchLexicalViewModel(private val searchLexicalUseCase: SearchLexicalUseCase) : ViewModel() {

    private val _searchResults = MutableLiveData<List<Aya>>(emptyList())
    val searchResults: LiveData<List<Aya>> = _searchResults

    private val _error =MutableLiveData<String>()
    val error :LiveData<String> =_error
    fun searchLexical(term: String) {
        viewModelScope.launch {
           try {
               val response = searchLexicalUseCase(term)
              // _searchResults.value = response.data?.flatMap { it.ayahs } ?: emptyList()
                Log.d("datasearch",response.data.toString())
           } catch (e : Exception){
               _error.value="Error :${e.message}"
               Log.d("datasearch",e.message.toString())
           }
        }
    }
}