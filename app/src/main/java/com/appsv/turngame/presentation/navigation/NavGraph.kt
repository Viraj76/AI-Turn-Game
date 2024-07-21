package com.appsv.turngame.presentation.navigation

import GameHistoryScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appsv.turngame.presentation.game_screen.GameScreen
import com.appsv.turngame.presentation.game_screen.GameViewModel
import com.appsv.turngame.presentation.login_screen.LoginScreen
import com.appsv.turngame.presentation.login_screen.UsersViewModel

@Composable
fun SetUpNavGraph(
    startDestination : String,
    viewModel: UsersViewModel,
    gameViewModel: GameViewModel
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination){
        composable(
            route = Screens.LoginScreen.route
        ){
           LoginScreen (viewModel,navController)
        }

        composable(route = Screens.GameScreen.route){
            GameScreen(gameViewModel,navController)
        }



        composable(route = Screens.GameHistoryScreen.route){
            val state by gameViewModel.gameHistoryList.collectAsState()
            GameHistoryScreen(state)
        }
    }

}