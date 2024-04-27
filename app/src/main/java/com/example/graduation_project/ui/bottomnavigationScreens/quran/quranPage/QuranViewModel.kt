package com.example.graduation_project.ui.bottomnavigationScreens.quran.quranPage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.ViewModel
import java.io.IOException


import java.text.DecimalFormat

class QuranViewModel:ViewModel() {

   /* fun getQuranImageByPageNumber(context: Context, pageNum: Int): Int {
      val drawableName = buildString {
            append("page")
            "%03d".format(pageNum).forEach {
                append(it.toInt().toChar())
            }
        }
        Log.d("Salma1", "Drawable Name: $drawableName")
        return context.resources.getIdentifier(drawableName, "drawable", context.packageName)
        //return PageManeger.getQuranImageByPageNumber(context,pageNum)
    }*/
   fun getQuranImageByPageNumber(context: Context, pageNum: Int): Bitmap? {
       val assetManager = context.assets
       val imageName ="new_imgs/${pageNum}.png"
       return try {
           assetManager.open(imageName).use { inputStream ->
               BitmapFactory.decodeStream(inputStream)
           }
       } catch (e: IOException) {
           e.printStackTrace()
           null
       }
   }
}