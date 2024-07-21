package com.appsv.turngame.presentation.login_screen

import com.appsv.turngame.data.local.room.users.UserEntity

sealed class LoginEvents{
    data class SaveUser(val userEntity: UserEntity) : LoginEvents()
}