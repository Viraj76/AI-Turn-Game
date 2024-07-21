package com.appsv.turngame.presentation.game_history

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appsv.turngame.data.local.room.history.GameHistory
import com.appsv.turngame.data.local.room.history.GameHistoryDao
import com.appsv.turngame.domain.repository.GameHistoryRepository


@Preview
@Composable
private fun prevv() {
    GameHistoryScreen(context = LocalContext.current)
}

@Composable
fun GameHistoryScreen(context: Context) {
    val repository = remember { GameHistoryRepository(context) }
    val viewModel: GameHistoryViewModel = viewModel(factory = GameHistoryViewModelFactory(repository))
    
    val history by viewModel.gameHistory
    val isEmpty by viewModel.isEmpty

    Column(modifier = Modifier.padding(16.dp)) {
        if (isEmpty) {
            Text("No game history available.", style = MaterialTheme.typography.bodyMedium)
        } else {
            LazyColumn {
                items(history) { gameHistory ->
                    GameHistoryItem(gameHistory)
                }
            }
        }
    }
}

@Composable
fun GameHistoryItem(gameHistory: GameHistory) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text("User Selections: ${gameHistory.userSelections.joinToString()}")
        Text("AI Selections: ${gameHistory.aiSelections.joinToString()}")
        Text("Outcome: ${gameHistory.gameOutcome}")
        Divider(modifier = Modifier.padding(vertical = 8.dp))
    }
}
