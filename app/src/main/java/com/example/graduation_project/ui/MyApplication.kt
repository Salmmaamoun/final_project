package com.example.graduation_project.ui

import android.app.Application

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import com.bumptech.glide.request.RequestOptions
import java.util.*

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        fun updateLocale(context: Context) {
            val language = getLanguage(context)
            setLocale(context, language)
        }

        private fun getLanguage(context: Context): String {
            return "en" // Default language is English
        }

        private fun setLocale(context: Context, language: String) {
            val locale = Locale(language)
            Locale.setDefault(locale)
            val config = Configuration(context.resources.configuration)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                config.setLocale(locale)
                context.createConfigurationContext(config)
            } else {
                config.locale = locale
                context.resources.updateConfiguration(config, context.resources.displayMetrics)
            }
        }
    }
}
