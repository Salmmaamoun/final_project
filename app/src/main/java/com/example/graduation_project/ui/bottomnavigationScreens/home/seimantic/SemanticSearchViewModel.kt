package com.example.graduation_project.ui.bottomnavigationScreens.home.seimantic

import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.AyaData
import com.example.domain.entity.HighlightResponce
import com.example.domain.usecase.SearchSemanticUseCase
import com.example.graduation_project.R
import kotlinx.coroutines.launch

class SemanticSearchViewModel(private val searchSemanticUseCase: SearchSemanticUseCase) :
    ViewModel() {

    private val _semanticResponse = MutableLiveData<List<AyaData>?>()
    val semanticResponce: MutableLiveData<List<AyaData>?> = _semanticResponse

    private val _highligthResponse = MutableLiveData<HighlightResponce?>()
    val highligthResponse: MutableLiveData<HighlightResponce?> = _highligthResponse

    private val _highlightedText = MutableLiveData<SpannableString>()
    val highlightedText: LiveData<SpannableString> = _highlightedText

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getSemanticSearchResponce(query: String) {
        _loading.value = true // Show loading indicator
        viewModelScope.launch {
            val responce = searchSemanticUseCase.getSemanticsearch(query)
            var ayaList = mutableListOf<Double>()
            responce.results.forEach {
                ayaList.add(it[1] as? Double ?: 0.0)
            }

            val lexicalList = mutableListOf<AyaData>()
            if (ayaList.size>=5) ayaList=ayaList.subList(0,6)
            ayaList.forEach {
                Log.d("aya query", it.toString())
                val output = searchSemanticUseCase.excuteApiSemantic(it.toInt() + 1)
                Log.d("aya responce", output.toString())
                lexicalList.add(output)

            }

            Log.d("SemanticResponse", responce.toString())
            _semanticResponse.postValue(lexicalList)
            _loading.value = false // Hide loading indicator after data is fetched
        }
    }

    fun getHighligth(question: String, context: String) {
        viewModelScope.launch {
            val response = searchSemanticUseCase.executeHighligth(question, context)
            Log.d("highligth in viewModel" ,response.toString())
            _highligthResponse.postValue(response)
        }
    }
}
