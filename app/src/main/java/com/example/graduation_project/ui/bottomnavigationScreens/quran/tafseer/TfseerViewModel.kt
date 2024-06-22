package com.example.graduation_project.ui.bottomnavigationScreens.quran.tafseer

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.database.QuranDatabase
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Tfseer
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.tafseerProvider.TfseerProvider

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale.filter

class TfseerViewModel(app: Application) : AndroidViewModel(app) {
    private val tfseerPage = TfseerProvider()
    val tfseerLiveData = MutableLiveData<List<Tfseer>>()
    var tfseerList = ArrayList<Tfseer>()
    val tfseerDao = QuranDatabase.getInstance(getApplication())?.tfseerDao()
    val dataLoaded = MutableLiveData<Boolean>()

    init {
        getAllTfseer()
    }

    private fun getAllTfseer() {
        viewModelScope.launch(Dispatchers.IO) {
            tfseerList = tfseerPage.getAllTfasser(getApplication())!!
            withContext(Dispatchers.Main) {
                tfseerLiveData.value = tfseerList
                dataLoaded.value = true // Indicate data is loaded
            }
        }
    }

    fun getTfseerByPage(soraNumber: String, ayaNumber: String): Tfseer? {
        return tfseerList
            .filter { it.number == soraNumber }
            .filter { it.aya == ayaNumber }
            .firstOrNull()
    }

    fun getTfseerAyaByPage(pageNumber: Int) =
        tfseerDao?.getPageAyatByNumber(pageNumber)
}