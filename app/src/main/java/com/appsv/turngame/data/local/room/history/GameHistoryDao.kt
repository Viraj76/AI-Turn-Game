package com.appsv.turngame.data.local.room.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameHistoryDao {

    @Insert
    suspend fun insertGameHistory(gameHistory: GameHistory)

    @Query("SELECT * FROM game_history")
    suspend fun getAllGameHistory(): List<GameHistory>
}
