import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appsv.turngame.data.local.room.history.GameHistory
import com.appsv.turngame.presentation.game_history.GameHistoryState
import com.appsv.turngame.presentation.game_screen.GameViewModel

@Composable
fun GameHistoryScreen(
    state: GameHistoryState
) {


    if (state.gameHistoryList.isEmpty()) {
        Text(
            text = "No history available",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }


    else {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)) {

            LazyColumn(
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.gameHistoryList) { gameHistory ->
                    GameHistoryItem(gameHistory = gameHistory)
                }
            }
        }
    }
}

@Composable
fun GameHistoryItem(gameHistory: GameHistory) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Column(

        ) {
            Text(
                text = "Date: ${gameHistory.timestamp}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Last User Selection: ${gameHistory.lastUserSelections}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Outcome: ${gameHistory.gameOutcome}",
                fontSize = 14.sp,
                color = Color.Red
            )
        }
    }
}
