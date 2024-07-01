package com.example.graduation_project.ui.bottomnavigationScreens.quran.data.database


import androidx.room.Dao
import androidx.room.Query
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Aya
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Juzz
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Sora

@Dao
interface QuranDao {
    @Query("SELECT * FROM quran WHERE page = :page")
    fun getAyatByPage(page: Int): List<Aya>
    @Query("SELECT sora as soraNumber, MIN(page) as startPage, MAX(page) as endPage, sora_name_ar as arabicName, sora_name_en as englishName FROM quran WHERE sora = :soraNumber")
    fun getSoraByNumber(soraNumber: Int): Sora
    @Query("SELECT jozz as jozzNumber, MIN(page) as startPage ,MAX(page) as endPage FROM quran WHERE jozz = :jozzNumber")
    fun getJozzByNumber(jozzNumber:Int) : Juzz
}