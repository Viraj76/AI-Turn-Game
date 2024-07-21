package com.appsv.turngame.data.repository


import com.appsv.turngame.data.local.room.users.UsersDao
import com.appsv.turngame.domain.repository.UserRepository

class UserRepositoryImpl(
    val usersDao: UsersDao
) : UserRepository {
    override suspend fun isLoggedIn(): Int {
        return usersDao.isLoggedIn()
    }


}