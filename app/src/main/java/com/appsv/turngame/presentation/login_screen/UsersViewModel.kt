package com.appsv.turngame.presentation.login_screen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.appsv.turngame.data.local.room.db.TurnAppDatabase
import com.appsv.turngame.data.repository.UserRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UsersViewModel(application: Application) : AndroidViewModel(application) {

    private val usersDao = TurnAppDatabase.getDatabaseInstance(application)?.usersDao()
    private val userRepository = UserRepositoryImpl(usersDao!!)


    private val _state = MutableStateFlow(LoginState())
    val state = _state


    init {
        isLoggedIn()
    }

    
    // function to check there is any user logged in or not, we can use datastore also do this or shared pref.
    private fun isLoggedIn() {
        viewModelScope.launch {
            val isLoggedIn = userRepository?.isLoggedIn()
            Log.d("APPLOG" , isLoggedIn.toString())
            if (isLoggedIn != null) {
                if(isLoggedIn > 0){
                    Log.d("APPLOG" , "yes")
                    _state.value = state.value.copy(login = true)
                }

            }
            else{
                Log.d("APPLOG" , "yes")
                _state.value = state.value.copy(login = false)
            }
        }

    }


}