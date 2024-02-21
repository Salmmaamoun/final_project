package com.example.graduation_project.ui.bottomnavigationScreens.quran.quranPage

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel


import java.text.DecimalFormat

class QuranViewModel:ViewModel() {

    fun getQuranImageByPageNumber(context: Context, pageNum: Int): Int {
      val drawableName = buildString {
            append("page")
            "%03d".format(pageNum).forEach {
                append(it.toInt().toChar())
            }
        }
        Log.d("Salma1", "Drawable Name: $drawableName")
        return context.resources.getIdentifier(drawableName, "drawable", context.packageName)
        //return PageManeger.getQuranImageByPageNumber(context,pageNum)
    }
}