package com.appsv.turngame.presentation.game_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.appsv.turngame.R
import com.appsv.turngame.data.local.room.history.GameHistory
import com.appsv.turngame.presentation.navigation.Screens

@SuppressLint("MutableCollectionMutableState")


@Composable
fun GameScreen(gameViewModel: GameViewModel,navController: NavController) {

    var showInfoDialog by remember { mutableStateOf(true) }
    var showInputDialog by remember { mutableStateOf(false) }
    var userSelection by remember { mutableStateOf("") }
    var boxStates by remember {
        mutableStateOf(mutableStateListOf<BoxState>().apply {
            repeat(21) {
                add(
                    BoxState.Unclicked
                )
            }
        })
    }

    var gameMessage by remember { mutableStateOf("") }
    var gameOver by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showGameOverDialog by remember { mutableStateOf(false) }

    if (showInfoDialog) {
        AlertDialog(
            onDismissRequest = {

            },
            title = {
                Text(
                    "Game Rules",
                    color = colorResource(id = R.color.white)
                )
            },
            text = {
                Text(
                    "Welcome to the game!\n\nRules:\n- You and the AI take turns selecting boxes.\n- You can select 1 to 4 boxes in your turn.\n- If you are forced to select the last box, you lose.",
                    color = colorResource(id = R.color.white)
                )
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
    }

    else if (showInputDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Select Number of Boxes", color = colorResource(id = R.color.white)) },
            text = {
                Column {
                    TextField(
                        value = userSelection.toString(),
                        onValueChange = { newValue ->
                            userSelection = (newValue.toIntOrNull() ?: "").toString()
                        },
                        label = { Text("Select 1 to 4 boxes", color = colorResource(id = R.color.white)) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors().copy(
                            focusedLabelColor = colorResource(id = R.color.white),
                            unfocusedLabelColor = colorResource(id = R.color.white),
                            focusedTextColor = colorResource(id = R.color.white),
                            unfocusedTextColor = colorResource(id = R.color.white)
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
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
                    if (userSelection.isNotEmpty()) {
                        if (userSelection.toInt() in 1..4) {
                            showInputDialog = false
                            errorMessage = ""
                            gameMessage = "User selected $userSelection box(es)."
                            userSelectBoxes(userSelection, boxStates) { resultMessage ->
                                gameMessage = resultMessage
                                if (boxStates.count { it == BoxState.Unclicked } > 1) {
                                    aiMove(userSelection, boxStates)
                                    val remainingBoxes =
                                        boxStates.count { it == BoxState.Unclicked }
                                    if (remainingBoxes == 1) {
                                        gameMessage = "You lost! The last box was selected."
                                        gameOver = true
                                        showGameOverDialog = true
                                    } else {
                                        gameMessage =
                                            "AI's turn done. Make your turn again by clicking the button below!"
                                    }
                                }
                            }
                        } else {
                            errorMessage = "Please select a number between 1 and 4."
                        }
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
    }

    else if (showGameOverDialog) {
        val gameHistory = GameHistory(
            lastUserSelections = userSelection.toInt(),
            gameOutcome = "You lost!"
        )

        // saving history..

        gameViewModel.saveGameHistory(gameHistory)

        AlertDialog(
            onDismissRequest = { },
            title = { Text("Game Over", color = colorResource(id = R.color.white)) },
            text = {
                Text(
                    "You lost! Would you like to play again?",
                    color = colorResource(id = R.color.white)
                )
            },
            confirmButton = {
                Button(onClick = {
                    boxStates = mutableStateListOf<BoxState>().apply { repeat(21) { add(BoxState.Unclicked) } }
                    gameMessage = ""
                    gameOver = false
                    showGameOverDialog = false
                    showInputDialog = true
                }) {
                    Text("Play Again")
                }
            },
            dismissButton = { }
        )
    }

    else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Column(modifier = Modifier.padding(10.dp)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        fontWeight = FontWeight.Bold,
                        text = "History",
                        color = Color.Yellow
                    )

                    Spacer(modifier = Modifier.width(2.dp))

                    IconButton(onClick = {
                        navController.navigate(Screens.GameHistoryScreen.route)
                    }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_history_24),
                            contentDescription = "History",
                            tint = Color.Yellow
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    text = gameMessage,
                    color = Color.White
                )

                Column {

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

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        val boxIndex = 20
                        GameBox(boxNumber = boxIndex, boxState = boxStates[boxIndex])
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                if (!gameOver) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { showInputDialog = true }
                    ) {
                        Text(
                            "Make Your Move"
                        )
                    }
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

fun userSelectBoxes(
    selection: String,
    boxStates: MutableList<BoxState>,
    onComplete: (String) -> Unit
) {
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
    val unclickedBoxes: List<Int> = boxStates.indices.filter { boxStates[it] == BoxState.Unclicked }
    val aiSelection: Int = (5 - userSelection.toInt()).coerceAtMost(unclickedBoxes.size)
    if (aiSelection > 0) {
        val aiSelectedBoxes: List<Int> = unclickedBoxes.take(aiSelection)
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
