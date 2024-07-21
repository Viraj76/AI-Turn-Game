package com.appsv.turngame.data.local.room.users

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UsersDao {


    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Query("select * from UserEntity WHERE username = :username AND password = :password")
    fun gerUser(username : String , password : String)

}