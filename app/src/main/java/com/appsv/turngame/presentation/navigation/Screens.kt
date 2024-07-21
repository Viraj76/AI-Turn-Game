package com.appsv.turngame.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


sealed class Screens(
    val route : String
){


    data object LoginScreen : Screens(route = "login_screen")
    data object GameScreen : Screens(route = "game_screen")
}