package com.example.graduation_project.ui.bottomnavigationScreens.Tafseer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.TafseerDataItem
import com.example.domain.entity.TafseerResponse
import com.example.domain.usecase.AyaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TafseerViewModel(private val useCase: AyaUseCase) : ViewModel() {
    private val _ayahData = MutableLiveData<TafseerDataItem?>()
    val ayahData: LiveData<TafseerDataItem?> = _ayahData
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchAyah(editionId: Int, surahNumber: Int, ayahNumber: Int) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = useCase(editionId, surahNumber, ayahNumber)
                val dataItem = response.data?.find {
                    it?.ayah?.numberInSurah == ayahNumber && it?.ayah?.surahId== surahNumber
                }
                _ayahData.postValue(dataItem)
                _isLoading.postValue(false)
            } catch (e: Exception) {
                _error.postValue("Failed to fetch data: ${e.message}")
                _isLoading.postValue(false)
            }
        }
    }
}