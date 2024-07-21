package com.appsv.turngame.presentation.game_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GameHistoryViewModelFactory(private val repository: GameHistoryRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameHistoryViewModel::class.java)) {
            return GameHistoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
