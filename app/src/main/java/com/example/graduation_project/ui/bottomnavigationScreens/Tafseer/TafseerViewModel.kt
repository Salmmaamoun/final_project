package com.example.graduation_project.ui.bottomnavigationScreens.Tafseer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.TafseerResponse
import com.example.domain.usecase.AyaUseCase
import kotlinx.coroutines.launch

class TafseerViewModel(private val useCase: AyaUseCase) : ViewModel() {
    private val _ayahData = MutableLiveData<String?>()
    val ayahData: MutableLiveData<String?> = _ayahData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchAyah(editionId: Int, surahNumber: Int, ayahNumber: Int) {
        viewModelScope.launch {
            try {
                val response = useCase.invoke(editionId,surahNumber,ayahNumber)
                val dataItem = response.data?.find { it?.ayah?.numberInSurah == ayahNumber&& it?.ayah?.surahId==surahNumber }
                val tafseerText = dataItem?.data
                _ayahData.postValue(tafseerText)

            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}