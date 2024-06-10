package com.example.graduation_project.ui.bottomnavigationScreens.home.lexical

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.LexicalResponse
import com.example.domain.entity.LexicalResponseItem
import com.example.domain.usecase.SearchLexicalUseCase
import kotlinx.coroutines.launch

class LexicalSearchViewModel(
    private val lexicalSearchUseCase: SearchLexicalUseCase
) : ViewModel() {
    private val _lexicalResponse = MutableLiveData<List<LexicalResponseItem>?>()
    val lexicalResponse: MutableLiveData<List<LexicalResponseItem>?> = _lexicalResponse

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun searchLexical(query: String) {
        _loading.value = true // Show loading indicator
        viewModelScope.launch {
            val response = lexicalSearchUseCase.searchLexical(query)
            _lexicalResponse.value = response
            _loading.value = false // Hide loading indicator after data is fetched
        }
    }
}


