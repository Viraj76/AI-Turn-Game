package com.appsv.turngame.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appsv.turngame.presentation.login_screen.LoginScreen

@Composable
fun SetUpNavGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.LoginScreen.route){
        composable(
            route = Screens.LoginScreen.route
        ){
            LoginScreen()
        }
    }

}