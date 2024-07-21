package com.appsv.turngame.data.local.room.history

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "game_history")
data class GameHistory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val lastUserSelections: Int,
    val gameOutcome: String,
    val timestamp: String = Date().toString()
)
