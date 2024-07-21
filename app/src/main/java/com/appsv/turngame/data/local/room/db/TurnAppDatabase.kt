package com.appsv.turngame.data.local.room.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.appsv.turngame.data.local.room.history.GameHistory
import com.appsv.turngame.data.local.room.history.GameHistoryDao
import com.appsv.turngame.data.local.room.users.UserEntity
import com.appsv.turngame.data.local.room.users.UsersDao

@Database(entities = [UserEntity::class, GameHistory::class], version = 2, exportSchema = false)
abstract class TurnAppDatabase : RoomDatabase(){

    abstract fun usersDao() : UsersDao
    abstract fun gameHistoryDap() : GameHistoryDao

    companion object{

        @Volatile
        var INSTANCE  : TurnAppDatabase? = null

        fun getDatabaseInstance(context: Context) : TurnAppDatabase? {
            val tempInstance = INSTANCE
            if(INSTANCE != null) return tempInstance
            synchronized(this){
                val roomdb = Room.databaseBuilder(context, TurnAppDatabase::class.java, "TurnAppDatabase").fallbackToDestructiveMigration().build()
                INSTANCE = roomdb
                return roomdb
            }
        }
    }



}