package com.appsv.turngame

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.appsv.turngame.presentation.game_screen.GameHistoryViewModelFactory
import com.appsv.turngame.presentation.game_screen.GameViewModel
import com.appsv.turngame.presentation.login_screen.LoginScreen
import com.appsv.turngame.presentation.login_screen.UsersViewModel
import com.appsv.turngame.presentation.navigation.Screens
import com.appsv.turngame.presentation.navigation.SetUpNavGraph
import com.appsv.turngame.ui.theme.TurnGameTheme

class MainActivity : ComponentActivity() {
    val viewModel: UsersViewModel by viewModels {
        UsersViewModelFactory(application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                Color(0xFF03045E).toArgb(),     // res color as int
            )
        )
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.splashCondition.value
            }
        }
        setContent {
            TurnGameTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.dark_blue)),


                ) {

                    val state by viewModel.state.collectAsState()

                    val gameViewModel : GameViewModel by viewModels {
                        GameHistoryViewModelFactory(application)
                    }

                    SetUpNavGraph(startDestination =  viewModel.startDestination , viewModel,gameViewModel)
                }
            }
        }
    }
}
