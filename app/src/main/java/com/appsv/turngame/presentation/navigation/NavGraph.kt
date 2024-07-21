package com.appsv.turngame.presentation.navigation

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appsv.turngame.UsersViewModelFactory
import com.appsv.turngame.presentation.game_screen.GameScreen
import com.appsv.turngame.presentation.login_screen.LoginScreen
import com.appsv.turngame.presentation.login_screen.UsersViewModel

@Composable
fun SetUpNavGraph(
    startDestination : String,
    viewModel: UsersViewModel
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination){
        composable(
            route = Screens.LoginScreen.route
        ){
           LoginScreen (viewModel,navController)
        }

        composable(route = Screens.GameScreen.route){
            GameScreen()
        }
    }

}