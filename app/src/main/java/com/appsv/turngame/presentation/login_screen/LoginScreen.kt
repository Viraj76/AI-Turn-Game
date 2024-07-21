package com.appsv.turngame.presentation.login_screen


import androidx.compose.foundation.layout.*

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.appsv.turngame.R
import com.appsv.turngame.common.NormalButton


@Composable
fun LoginScreen() {


    Column(

    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = "Enter you username...",
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
            onValueChange = {

            },
            label = { Text(text = "Enter your username") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {

                },
            ),
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = "Enter you username...",
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
            onValueChange = {

            },
            label = { Text(text = "Enter your password") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {

                },
            ),
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
        )

        Spacer(modifier = Modifier.height(8.dp))

        NormalButton(text = "Login") {

        }
    }

}




