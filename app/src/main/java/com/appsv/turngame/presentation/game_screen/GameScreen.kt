package com.appsv.turngame.presentation.game_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appsv.turngame.R

@Preview
@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    var showInfoDialog by remember { mutableStateOf(true) }
    var showInputDialog by remember { mutableStateOf(false) }
    var userSelection by remember { mutableStateOf("") }
    var boxStates by remember { mutableStateOf(mutableStateListOf<BoxState>().apply { repeat(21) { add(BoxState.Unclicked) } }) }
    var gameMessage by remember { mutableStateOf("") }
    var gameOver by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showGameOverDialog by remember { mutableStateOf(false) }

    if (showInfoDialog) {
        AlertDialog(
            onDismissRequest = { /* Handle dismiss */ },
            title = { Text("Game Rules") },
            text = {
                Text("Welcome to the game!\n\nRules:\n- You and the AI take turns selecting boxes.\n- You can select 1 to 4 boxes in your turn.\n- The AI will select the remaining boxes to make the total 5.\n- If you are forced to select the last box, you lose.")
            },
            confirmButton = {
                Button(onClick = {
                    showInfoDialog = false
                    showInputDialog = true
                }) {
                    Text("OK")
                }
            }
        )
    } else if (showInputDialog) {
        AlertDialog(
            onDismissRequest = { /* Handle dismiss */ },
            title = { Text("Select Number of Boxes") },
            text = {
                Column {
                    TextField(
                        value = userSelection.toString(),
                        onValueChange = { newValue ->
                            userSelection = (newValue.toIntOrNull() ?: "").toString()
                        },
                        label = { Text("Select 1 to 4 boxes") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (errorMessage.isNotEmpty()) {
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            },
            confirmButton = {
                Button(onClick = {
                    if (userSelection.toInt() in 1..4) {
                        showInputDialog = false
                        errorMessage = ""
                        gameMessage = "User selected $userSelection box(es)."
                        userSelectBoxes(userSelection, boxStates) { resultMessage ->
                            gameMessage = resultMessage
                            if (boxStates.count { it == BoxState.Unclicked } > 1) {
                                aiMove(userSelection, boxStates)
                                val remainingBoxes = boxStates.count { it == BoxState.Unclicked }
                                if (remainingBoxes == 1) {
                                    gameMessage = "You lost! The last box was selected."
                                    gameOver = true
                                    showGameOverDialog = true
                                } else {
                                    gameMessage = "Your turn is over. AI's turn."
                                }
                            }
                        }
                    } else {
                        errorMessage = "Please select a number between 1 and 4."
                    }
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(onClick = { showInputDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    } else if (showGameOverDialog) {
        AlertDialog(
            onDismissRequest = { /* Handle dismiss */ },
            title = { Text("Game Over") },
            text = { Text("You lost! Would you like to play again?") },
            confirmButton = {
                Button(onClick = {
                    // Reset the game
                    boxStates = mutableStateListOf<BoxState>().apply { repeat(21) { add(BoxState.Unclicked) } }
                    gameMessage = ""
                    gameOver = false
                    showGameOverDialog = false
                    showInputDialog = true
                }) {
                    Text("Play Again")
                }
            },
            dismissButton = {
                Button(onClick = { /* Exit game or go back */ }) {
                    Text("Exit")
                }
            }
        )
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = gameMessage)
            Column {
                // 5 rows of 4 boxes each
                repeat(5) { rowIndex ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(1.dp)
                    ) {
                        repeat(4) { columnIndex ->
                            val boxIndex = rowIndex * 4 + columnIndex
                            if (boxIndex < 20) {
                                GameBox(boxNumber = boxIndex, boxState = boxStates[boxIndex])
                            }
                        }
                    }
                }
                // 1 row of 1 box in the center
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    val boxIndex = 20
                    GameBox(boxNumber = boxIndex, boxState = boxStates[boxIndex])
                }
            }

            if (!gameOver) {
                Button(onClick = { showInputDialog = true }) {
                    Text("Make Your Move")
                }
            }
        }
    }
}

@Composable
fun GameBox(
    boxNumber: Int,
    boxState: BoxState
) {
    Box(
        modifier = Modifier
            .padding(1.dp)
            .background(
                color = when (boxState) {
                    BoxState.Unclicked -> colorResource(id = R.color.medium_blue1)
                    BoxState.UserClicked -> Color.Green
                    BoxState.AiClicked -> Color.Red
                },
                shape = CircleShape
            )
            .size(80.dp),
    )
}

fun userSelectBoxes(selection: String, boxStates: MutableList<BoxState>, onComplete: (String) -> Unit) {
    var count = 0
    for (i in boxStates.indices) {
        if (boxStates[i] == BoxState.Unclicked && count < selection.toInt()) {
            boxStates[i] = BoxState.UserClicked
            count++
        }
    }
    if (count == selection.toInt()) {
        onComplete("User selected $selection box(es).")
    } else {
        onComplete("Failed to select the required number of boxes.")
    }
}

fun aiMove(userSelection: String, boxStates: MutableList<BoxState>) {
    val unclickedBoxes = boxStates.indices.filter { boxStates[it] == BoxState.Unclicked }
    val aiSelection = (5 - userSelection.toInt()).coerceAtMost(unclickedBoxes.size)
    if (aiSelection > 0) {
        val aiSelectedBoxes = unclickedBoxes.take(aiSelection)
        for (boxIndex in aiSelectedBoxes) {
            boxStates[boxIndex] = BoxState.AiClicked
        }
    }
}

enum class BoxState {
    Unclicked,
    UserClicked,
    AiClicked
}
