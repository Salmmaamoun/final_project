package com.example.graduation_project.ui.bottomnavigationScreens.quran.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Aya




    @Database(entities = [Aya::class], version = 1)
 abstract class QuranDatabase : RoomDatabase() {

        abstract fun quranDao(): QuranDao

        companion object {
            @Volatile
            private var instance: QuranDatabase? = null

            fun getInstance(context: Context): QuranDatabase {
                return instance ?: synchronized(this) {
                    instance ?: buildDatabase(context).also { instance = it }
                }
            }

            private fun buildDatabase(context: Context): QuranDatabase {
                val DATABASE_NAME = "quran.db"
                return Room.databaseBuilder(context.applicationContext, QuranDatabase::class.java, DATABASE_NAME)
                    .createFromAsset("databases/quran.db")
                    .allowMainThreadQueries() // Note: Avoid using this method in production code
                    .build()
            }
        }
    }

