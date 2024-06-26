package com.example.graduation_project.ui.bottomnavigationScreens.quran

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.database.QuranDao
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.database.QuranDatabase
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Juzz
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Sora
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JuzzViewModel(application: Application) : AndroidViewModel(application) {
    var juzzDao : QuranDao? = null

     fun getAllData(): ArrayList<Juzz>{
         val dao = QuranDatabase.getInstance(getApplication()).quranDao()

         val juzz = ArrayList<Juzz>()
            for (i in 1..30) {
                juzz.add(dao!!.getJozzByNumber(i))
            }
         return juzz
     }




}