package com.appsv.turngame.domain.repository

import com.appsv.turngame.data.local.room.history.GameHistory

interface GameHistoryRepository {

    suspend fun saveGame(gameHistory: GameHistory)

    suspend fun fetchSavedGames() : List<GameHistory>
}