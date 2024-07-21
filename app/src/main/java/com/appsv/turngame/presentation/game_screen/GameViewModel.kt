package com.appsv.turngame.presentation.game_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.appsv.turngame.data.local.room.db.TurnAppDatabase
import com.appsv.turngame.data.local.room.history.GameHistory
import com.appsv.turngame.data.repository.GameHistoryRepositoryImpl
import com.appsv.turngame.presentation.game_history.GameHistoryState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val gameHistoryDao = TurnAppDatabase.getDatabaseInstance(application)?.gameHistoryDap()
    private val gameHistoryRepositoryRepository = GameHistoryRepositoryImpl(gameHistoryDao!!)


    fun saveGameHistory(gameHistory: GameHistory) {
        viewModelScope.launch {
            gameHistoryRepositoryRepository.saveGame(gameHistory)
        }
    }

    private val _gameHistoryList = MutableStateFlow(GameHistoryState())
    val gameHistoryList = _gameHistoryList

    init {
        fetchGameHistory()
    }

    private fun fetchGameHistory() {
        viewModelScope.launch {
            _gameHistoryList.value = gameHistoryList.value.copy( gameHistoryDao!!.getAllGameHistory())
        }
    }
}