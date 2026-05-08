package com.rudramaurya.task.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PrefManager(context: Context) {

    private val pref = context.getSharedPreferences("MY_PREF", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveList(
        key: String,
        value: String
    ) {
        val oldList = getList(key)
        oldList.add(0, value)
        val json = gson.toJson(oldList)
        pref.edit()
            .putString(key, json)
            .apply()
    }

    fun getList(
        key: String
    ): ArrayList<String> {
        val json = pref.getString(key, null)
        return if (json != null) {
            val type = object : TypeToken<ArrayList<String>>() {}.type
            gson.fromJson(json, type)
        } else {
            arrayListOf()
        }
    }

    fun saveTheme(
        isDark: Boolean
    ) {
        pref.edit()
            .putBoolean("dark_mode", isDark)
            .apply()
    }

    fun isDarkMode(): Boolean {
        return pref.getBoolean("dark_mode", false)
    }
}