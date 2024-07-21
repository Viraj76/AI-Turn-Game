package com.appsv.turngame.domain.repository

import AppDatabase
import android.content.Context
import androidx.room.Room
import com.appsv.turngame.data.local.room.history.GameHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameHistoryRepository(private val context: Context) {

    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "game-database"
    ).build()
    
    private val gameHistoryDao = db.gameHistoryDao()

    suspend fun getAllGameHistory(): List<GameHistory> = withContext(Dispatchers.IO) {
        gameHistoryDao.getAllGameHistory()
    }
}
