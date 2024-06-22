
package com.example.graduation_project.ui.bottomnavigationScreens.quran.data

import android.content.Context
import java.text.DecimalFormat

class PageManeger {
companion object{
    fun getQuranImageByPageNumber(context: Context, pageNum:Int):Int{
        var formatter: DecimalFormat=DecimalFormat("000")
        var drawableName:String="page"+formatter.format(pageNum)
        return context.resources.getIdentifier(drawableName,"drawable",context.packageName)
    }
}
}