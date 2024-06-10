package com.example.graduation_project.ui.bottomnavigationScreens.quran.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Aya

@Dao
interface TfseerDao {

    @Query("SELECT * FROM quran WHERE page = :pageNumber")
    fun getPageAyatByNumber(pageNumber: Int): LiveData<List<Aya>>

}