package com.example.graduation_project.ui.bottomnavigationScreens.quran

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.database.QuranDatabase
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Sora

class ListViewModel(application: Application) : AndroidViewModel(application){
    fun getAllSoras(): ArrayList<Sora> {
        val dao = QuranDatabase.getInstance(getApplication()).quranDao()
        val soras = ArrayList<Sora>()
        for (i in 1..114) {
            soras.add(dao.getSoraByNumber(i))
        }
        return soras
    }



    fun getPages(): List<Int> {
        return (1 until 604).toList()
    }


}