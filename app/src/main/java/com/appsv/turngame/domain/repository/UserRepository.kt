package com.appsv.turngame.domain.repository

import com.appsv.turngame.data.local.room.users.UserEntity

interface UserRepository {

   suspend fun isLoggedIn() : Int?

}