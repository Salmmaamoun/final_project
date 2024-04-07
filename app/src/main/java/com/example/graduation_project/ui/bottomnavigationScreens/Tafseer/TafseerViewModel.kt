package com.example.graduation_project.ui.bottomnavigationScreens.Tafseer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.TafseerDataItem
import com.example.domain.entity.TafseerResponse
import com.example.domain.usecase.AyaUseCase
import kotlinx.coroutines.launch

class TafseerViewModel(private val useCase: AyaUseCase) : ViewModel() {
    private val _ayahData = MutableLiveData<TafseerDataItem?>()
    val ayahData: LiveData<TafseerDataItem?> = _ayahData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchAyah(editionId: Int, surahNumber: Int, ayahNumber: Int) {
        viewModelScope.launch {
            try {
                val response = useCase(editionId, surahNumber, ayahNumber)
                    Log.d("response",response.data.toString())
                val dataItem = response.data?.find {
                    it?.ayah?.numberInSurah == ayahNumber && it?.ayah?.surahId == surahNumber
                }
                _ayahData.value = dataItem
                Log.d("tafseer1", "${_ayahData.value?.data}")
                Log.d("taf", "numaya ${_ayahData.value?.ayah?.numberInSurah} num surah ${_ayahData.value?.ayah?.surahId}")
            } catch (e: Exception) {
                _error.value = "Failed to fetch data: ${e.message}"
            }
        }
    }
}