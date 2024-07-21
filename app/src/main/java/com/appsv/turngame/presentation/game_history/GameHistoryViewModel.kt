//package com.appsv.turngame.presentation.game_history
//
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.State
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.appsv.turngame.data.local.room.history.GameHistory
//import kotlinx.coroutines.launch
////
//class GameHistoryViewModel(private val repository: GameHistoryRepository) : ViewModel() {
//
//    private val _gameHistory = mutableStateOf<List<GameHistory>>(emptyList())
//    val gameHistory: State<List<GameHistory>> get() = _gameHistory
//
//    private val _isEmpty = mutableStateOf(false)
//    val isEmpty: State<Boolean> get() = _isEmpty
//
//    init {
//        fetchGameHistory()
//    }
//
//    private fun fetchGameHistory() {
//        viewModelScope.launch {
//            val history = repository.getAllGameHistory()
//            _gameHistory.value = history
//            _isEmpty.value = history.isEmpty()
//        }
//    }
//}
