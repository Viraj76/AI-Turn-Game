package com.appsv.turngame.presentation.game_history

import com.appsv.turngame.data.local.room.history.GameHistory

data class GameHistoryState (
    val gameHistoryList : List<GameHistory> = emptyList()
)