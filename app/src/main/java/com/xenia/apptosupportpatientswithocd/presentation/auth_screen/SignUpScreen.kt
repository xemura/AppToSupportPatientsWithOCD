package com.xenia.apptosupportpatientswithocd.presentation.auth_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xenia.apptosupportpatientswithocd.presentation.composable.login_password_fields.LoginField
import com.xenia.apptosupportpatientswithocd.presentation.composable.login_password_fields.PasswordField
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    viewModel: AuthViewModel
) {
    var loginText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var repeatPasswordText by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val state = viewModel.signUpState.collectAsState(initial = null)

    var screenLogin by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (screenLogin) {
            SignInScreen(viewModel = viewModel)
        } else {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                text = "Присоединяйся к нам!",
                fontSize = 22.sp,
                textAlign = TextAlign.Start,
                color = Color(0xFF0575e6),
                fontWeight = FontWeight.Bold,
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                text = "Это первый шаг к тому, чтобы стало лучше.",
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            LoginField(
                value = loginText,
                onValueChange = {
                    loginText = it
                })

            PasswordField(value = passwordText,
                "Введите пароль",
                onValueChange = {
                    passwordText = it
                })

            PasswordField(value = repeatPasswordText,
                "Повторите пароль",
                onValueChange = {
                    repeatPasswordText = it
                })

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            Button(
                onClick = {
                    viewModel.registerUser(loginText, passwordText)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Text(
                    color = Color.White,
                    text = "Зарегистрироваться",
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 5.dp))

            Text(
                modifier = Modifier.clickable {
                    screenLogin = true
                },
                color = Color.Gray,
                text = "Есть аккаунта? Войти"
            )
        }
    }

    LaunchedEffect(key1 = state.value?.isSuccess) {
        scope.launch {
            if (state.value?.isSuccess?.isNotEmpty() == true) {
                val success = state.value?.isSuccess
                Toast.makeText(context, "$success", Toast.LENGTH_SHORT).show()
            }
        }
    }

    LaunchedEffect(key1 = state.value?.isError) {
        scope.launch {
            if (state.value?.isError?.isNotEmpty() == true) {
                val error = state.value?.isError
                Toast.makeText(context, "$error", Toast.LENGTH_SHORT).show()
            }
        }

    }
}