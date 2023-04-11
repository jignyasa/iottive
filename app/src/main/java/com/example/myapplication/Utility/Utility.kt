package com.example.myapplication.Utility

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log


class Utility {
    companion object{
        fun isConnected(context:Context): Boolean {
            var connected = false
            try {
                val cm = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val nInfo = cm.activeNetworkInfo
                connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
                return connected
            } catch (e: Exception) {
                Log.e("Connectivity Exception", e.message.toString())
            }
            return connected
        }
    }
}