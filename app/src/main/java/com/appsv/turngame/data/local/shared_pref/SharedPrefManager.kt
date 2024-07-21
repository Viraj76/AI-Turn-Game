package com.appsv.turngame.data.local.shared_pref

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(
    private val context: Context
){
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("stored_chapters", Context.MODE_PRIVATE)
    }

    fun userLoggedIn(){
        sharedPreferences.edit().putBoolean("user_loggin" , true).apply()
    }

    fun checkUserStatus() : Boolean{
        return sharedPreferences.getBoolean("user_loggin",false)
    }
}