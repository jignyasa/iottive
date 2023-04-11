package com.example.myapplication

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
    companion object {
        private val TAG = BaseApplication::class.java.simpleName
        lateinit  var appContext: Context
    }
}