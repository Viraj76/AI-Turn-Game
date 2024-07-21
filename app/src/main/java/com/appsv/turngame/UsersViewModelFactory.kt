package com.appsv.turngame

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appsv.turngame.presentation.login_screen.UsersViewModel

class UsersViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
