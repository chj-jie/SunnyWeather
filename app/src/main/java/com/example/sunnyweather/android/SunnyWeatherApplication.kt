package com.example.sunnyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication: Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val TOKEN = "ob7kTcasDRzTAGqL"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}