package com.appsv.turngame.data.local.room.users

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserEntity(
    @PrimaryKey
    val id : Int ?  = null,
    val username : String,
    val password : String
    )