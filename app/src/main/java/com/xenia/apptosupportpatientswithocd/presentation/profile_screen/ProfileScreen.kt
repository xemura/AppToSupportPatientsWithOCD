package com.xenia.apptosupportpatientswithocd.presentation.profile_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.xenia.apptosupportpatientswithocd.presentation.auth_screen.AuthViewModel
import com.xenia.apptosupportpatientswithocd.presentation.auth_screen.SignInScreen
import com.xenia.apptosupportpatientswithocd.presentation.composable.GradientSwitch
import com.xenia.apptosupportpatientswithocd.ui.theme.ButtonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: AuthViewModel
) {

    var name by remember { mutableStateOf("") }
    var screenLogin by remember { mutableStateOf(false) }
    var switchState by remember { mutableStateOf(false) }

    if (screenLogin) {
        SignInScreen(viewModel = viewModel)
    } else {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    modifier = Modifier
                        .shadow(
                            elevation = 5.dp,
                            spotColor = Color.DarkGray
                        ),
                    title = {
                        Text(text = "Профиль")
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        titleContentColor = Color.White,
                        containerColor = Color(0xFF101018)
                    )
                )
            }
        ) { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(top = contentPadding.calculateTopPadding() + 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    color = Color.Black,
                    text = "А тут написано как вас зовут",
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp),
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text(text = "Введите ваше имя") },
                    label = { Text("Ваше имя") },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color(0xFF0575e6),
                        unfocusedIndicatorColor = Color.Black,
                        focusedLabelColor = Color(0xFF0575e6),
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                )

                Card(
                    modifier = Modifier
                        .padding(horizontal = 50.dp, vertical = 20.dp)
                        .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(10.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 30.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Ежедневные\nнапоминания")

                            GradientSwitch(
                                switchState,
                                onCheckedChange = {
                                    switchState = it
                                }
                            )
                        }
                        
                        Spacer(modifier = Modifier.padding(vertical = 5.dp))

                        Row(
                            modifier = Modifier
                                .padding(horizontal = 30.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Время уведомлений")
                            Text(text = "20:00")
                        }
                    }
                }

                Button(
                    onClick = { /* do something on click */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .width(150.dp)
                ) {
                    Text(
                        color = Color.White,
                        text = "Сохранить",
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 5.dp)
                        .clickable {
                            viewModel.signOut()
                            screenLogin = true
                        }
                        .alpha(0.7f),
                    text = "Выйти",
                    color = Color.Red
                )
            }
        }
    }


}