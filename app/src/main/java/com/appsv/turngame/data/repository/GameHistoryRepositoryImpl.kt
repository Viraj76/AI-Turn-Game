package com.appsv.turngame.data.repository

import com.appsv.turngame.data.local.room.history.GameHistory
import com.appsv.turngame.data.local.room.history.GameHistoryDao
import com.appsv.turngame.domain.repository.GameHistoryRepository

class GameHistoryRepositoryImpl(
    private val gameHistoryDao: GameHistoryDao
) : GameHistoryRepository{
    override suspend fun saveGame(gameHistory: GameHistory) {
        gameHistoryDao.insertGameHistory(gameHistory)
    }

    override suspend fun fetchSavedGames(): List<GameHistory> {
        return gameHistoryDao.getAllGameHistory()
    }
}