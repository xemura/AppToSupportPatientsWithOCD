package com.xenia.apptosupportpatientswithocd.presentation.auth_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.xenia.apptosupportpatientswithocd.navigation.NavigationItem
import com.xenia.apptosupportpatientswithocd.navigation.rememberNavigationState

@Preview
@Composable
fun SignUpScreen() {
    var loginText by remember { mutableStateOf("Введите логин") }
    var passwordText by remember { mutableStateOf("Введите пароль") }
    var repeatPasswordText by remember { mutableStateOf("Повторите пароль") }

    val navigationState = rememberNavigationState()

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Добро пожаловать!")

        OutlinedTextField(
            value = loginText,
            onValueChange = { loginText = it },
            label = { Text("Логин") }
        )

        OutlinedTextField(
            value = passwordText,
            onValueChange = { passwordText = it },
            label = { Text("Пароль") }
        )

        OutlinedTextField(
            value = repeatPasswordText,
            onValueChange = { repeatPasswordText = it },
            label = { Text("Повторите пароль") }
        )

        Button(onClick = {
            navigationState.navigateTo(NavigationItem.Main.route)
        }) {
            Text(text = "Зарегистрироваться")
        }

        Text(text = "Есть аккаунта? Войти")
    }
}