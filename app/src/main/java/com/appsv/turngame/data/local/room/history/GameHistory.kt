package com.appsv.turngame.data.local.room.history

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "game_history")
data class GameHistory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userSelections: List<Int>,
    val aiSelections: List<Int>,
    val gameOutcome: String
)
