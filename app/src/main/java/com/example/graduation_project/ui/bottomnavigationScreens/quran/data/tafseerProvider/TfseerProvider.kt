package com.example.graduation_project.ui.bottomnavigationScreens.quran.data.tafseerProvider

import android.content.Context

import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Tfseer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.nio.charset.StandardCharsets

class TfseerProvider (){
     fun getAllTfasser(context: Context): ArrayList<Tfseer>? {
        return try {
            val azkarFile = context.assets.open("tfseer.json")
            val size = azkarFile.available()
            val bytes = ByteArray(size)
            azkarFile.read(bytes)
            azkarFile.close()
            val tfserString = String(bytes, StandardCharsets.UTF_8)
            val gson = Gson()
            gson.fromJson(tfserString, object : TypeToken<List<Tfseer?>?>(){}.type)
        } catch (e: IOException) {
            e.printStackTrace()
            null

    }
    }


}