package com.appsv.turngame.presentation.login_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import com.appsv.turngame.R
import com.appsv.turngame.common.NormalButton
import com.appsv.turngame.data.local.room.users.UserEntity
import com.appsv.turngame.presentation.navigation.Screens

@Composable
fun LoginScreen(
    viewModel: UsersViewModel,
    navController: NavController
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(modifier = Modifier.padding(16.dp)) {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = username,
                colors = OutlinedTextFieldDefaults.colors().copy(
                    focusedTextColor = colorResource(id = R.color.light_blue),
                    unfocusedTextColor = colorResource(id = R.color.light_blue),
                    focusedContainerColor = colorResource(id = R.color.dark_blue),
                    unfocusedContainerColor = colorResource(id = R.color.dark_blue),
                    focusedLeadingIconColor = colorResource(id = R.color.light_blue),
                    unfocusedLeadingIconColor = colorResource(id = R.color.light_blue),
                    focusedIndicatorColor = colorResource(id = R.color.light_blue),
                    unfocusedIndicatorColor = colorResource(id = R.color.light_blue),
                    focusedLabelColor = colorResource(id = R.color.light_blue),
                    unfocusedLabelColor = colorResource(id = R.color.light_blue),
                    cursorColor = colorResource(id = R.color.light_blue)
                ),
                onValueChange = { username = it },
                label = { Text(text = "Enter your username" , color = Color.White)  },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {  },
                ),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = password,
                colors = OutlinedTextFieldDefaults.colors().copy(
                    focusedTextColor = colorResource(id = R.color.light_blue),
                    unfocusedTextColor = colorResource(id = R.color.light_blue),
                    focusedContainerColor = colorResource(id = R.color.dark_blue),
                    unfocusedContainerColor = colorResource(id = R.color.dark_blue),
                    focusedLeadingIconColor = colorResource(id = R.color.light_blue),
                    unfocusedLeadingIconColor = colorResource(id = R.color.light_blue),
                    focusedIndicatorColor = colorResource(id = R.color.light_blue),
                    unfocusedIndicatorColor = colorResource(id = R.color.light_blue),
                    focusedLabelColor = colorResource(id = R.color.light_blue),
                    unfocusedLabelColor = colorResource(id = R.color.light_blue),
                    cursorColor = colorResource(id = R.color.light_blue)
                ),
                onValueChange = { password = it },
                label = { Text(text = "Enter your password",color = Color.White) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            NormalButton(text = "Login") {
                val validLogin = (username == "admin" && password == "admin1234") ||
                        (username == "guest" && password == "guest1234")

                if (validLogin) {
                    val userEntity = UserEntity(username = username, password = password)
                    viewModel.saveUser(userEntity)
                    errorMessage = ""
                    navController.navigate(Screens.GameScreen.route){

                    }
                } else {
                    errorMessage = "Invalid username or password. Please try again."
                }
            }
        }

    }
}
