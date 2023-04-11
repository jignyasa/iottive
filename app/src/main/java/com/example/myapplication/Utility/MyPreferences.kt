package com.example.myapplication.Utility

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class MyPreferences(context:Context) {
    val sharedPreferences:SharedPreferences=context.getSharedPreferences("MySharedPref", MODE_PRIVATE);

    var userId:Int by IntPreference(sharedPreferences,"USER_ID",0)
    var authToken:String? by StringPreference(sharedPreferences,"AUTH_TOKEN","")
    var isLoggedIn:Boolean by BooleanPreference(sharedPreferences,"LOGGED_IN",false)
}

class StringPreference(var sharedPreferences: SharedPreferences,var s: String, var s1: String) :
    ReadWriteProperty<MyPreferences, String?> {
    override fun getValue(thisRef: MyPreferences, property: KProperty<*>): String? {

        return sharedPreferences.getString(s,s1)
    }

    override fun setValue(thisRef: MyPreferences, property: KProperty<*>, value: String?) {
        sharedPreferences.edit().putString(s,value).apply()
    }

}

class BooleanPreference(
    private var preferences: SharedPreferences,
    private var name: String,
    private var defaultValue: Boolean,
) : ReadWriteProperty<Any, Boolean> {
    override fun getValue(
        thisRef: Any,
        property: KProperty<*>,
    ): Boolean {
        return preferences.getBoolean(name, defaultValue)
    }

    override fun setValue(
        thisRef: Any,
        property: KProperty<*>,
        value: Boolean,
    ) {
        preferences.edit().putBoolean(name, value).apply()
    }
}

class IntPreference(
    private var preferences: SharedPreferences,
    private var name: String,
    private var defaultValue: Int,
) : ReadWriteProperty<Any, Int> {
    override fun getValue(
        thisRef: Any,
        property: KProperty<*>,
    ): Int {
        return preferences.getInt(name, defaultValue)
    }

    override fun setValue(
        thisRef: Any,
        property: KProperty<*>,
        value: Int,
    ) {
        preferences.edit().putInt(name, value).apply()
    }
}
