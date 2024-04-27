package com.example.graduation_project.ui.bottomnavigationScreens.Tafseer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.DataItem
import com.example.domain.entity.SurahResponse
import com.example.domain.usecase.SignUpUseCase
import com.example.domain.usecase.SurahNameUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SurahNameViewModel(private val useCase: SurahNameUseCase):ViewModel() {

    private val _dataItems = MutableLiveData<List<DataItem>?>()
    val dataItems: MutableLiveData<List<DataItem>?> = _dataItems
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchData(language: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = useCase.invoke(language)
                _dataItems.postValue(response.data as List<DataItem>?)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

}