package com.appsv.turngame.presentation.login_screen

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.appsv.turngame.data.local.room.db.TurnAppDatabase
import com.appsv.turngame.data.local.room.users.UserEntity
import com.appsv.turngame.data.repository.UserRepositoryImpl
import com.appsv.turngame.presentation.navigation.Screens
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UsersViewModel(application: Application) : AndroidViewModel(application) {

    private val usersDao = TurnAppDatabase.getDatabaseInstance(application)?.usersDao()
    private val userRepository = UserRepositoryImpl(usersDao!!)

    private val _splashCondition = mutableStateOf(true)
    val splashCondition: MutableState<Boolean> = _splashCondition

    private val _state = MutableStateFlow(LoginState())
    val state = _state

    var startDestination by mutableStateOf(Screens.LoginScreen.route)
        private set


    init {
        isLoggedIn()
    }

    fun saveUser(userEntity: UserEntity){
        viewModelScope.launch {
            userRepository.insertUser(userEntity)
        }
    }
    
    // function to check there is any user logged in or not, we can use datastore also do this or shared pref.
    private fun isLoggedIn() {
        viewModelScope.launch {
            val isLoggedIn = userRepository.isLoggedIn()
            if(isLoggedIn > 0){
                startDestination = Screens.GameScreen.route
            }
            delay(300)
            _splashCondition.value = false
        }
    }



}