package com.xenia.apptosupportpatientswithocd.presentation.profile_screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xenia.apptosupportpatientswithocd.domain.entity.UserModel
import com.xenia.apptosupportpatientswithocd.presentation.auth_screen.AuthViewModel
import com.xenia.apptosupportpatientswithocd.presentation.auth_screen.SignInScreen
import com.xenia.apptosupportpatientswithocd.presentation.composable.GradientSwitch
import com.xenia.apptosupportpatientswithocd.presentation.getApplicationComponent

@Composable
fun ProfileScreenContent(
    onSaveButtonPressed: () -> Unit,
    viewModel: AuthViewModel,
) {
    val component = getApplicationComponent()
    val profileViewModel: ProfileViewModel = viewModel(factory = component.getViewModelFactory())

    val screenState = profileViewModel.screenState.collectAsState(ProfileScreenState.Initial)

    when (val currentState = screenState.value) {
        is ProfileScreenState.Profile -> {
            Log.d("TAG", "Profile")
            ProfileScreen(
                viewModel = viewModel,
                onSaveButtonPressed = { onSaveButtonPressed() },
                userInfo = currentState.userInfo
            )
        }
        ProfileScreenState.Initial -> {
            Log.d("TAG", "Initial")
        }
        ProfileScreenState.Loading -> {
            Log.d("TAG", "Loading")
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center)
            {
                CircularProgressIndicator(color = Color.Black)
            }
        }

        else -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onSaveButtonPressed: () -> Unit,
    viewModel: AuthViewModel,
    userInfo: UserModel
) {

    Log.d("TAG", "ProfileScreen = $userInfo")

    var name by remember { mutableStateOf(userInfo.name) }
    var screenLogin by remember { mutableStateOf(false) }
    var switchState by remember { mutableStateOf(userInfo.notificationEnable) }

    var showDialog by remember {
        mutableStateOf(false)
    }

    //val hour = userInfo.notificationTime.take(2).toInt()
    var selectedHour by remember { mutableIntStateOf(userInfo.notificationTime.take(2).toInt()) }
    var selectedMinute by remember { mutableIntStateOf(userInfo.notificationTime.drop(3).toInt()) }
    val timeState = rememberTimePickerState(
        initialHour = selectedHour,
        initialMinute = selectedMinute,
        true
    )

    val component = getApplicationComponent()
    val profileViewModel: ProfileViewModel = viewModel(factory = component.getViewModelFactory())

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

            if (showDialog) {
                BasicAlertDialog(
                    onDismissRequest = { showDialog = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(20.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .background(color = Color.White)
                            .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TimePicker(
                            modifier = Modifier.background(Color.White),
                            state = timeState,
                            colors = TimePickerDefaults.colors(
                                containerColor = Color.White
                            )
                        )
                        Row(
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .fillMaxWidth(), horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(onClick = { showDialog = false }) {
                                Text(text = "Отмена")
                            }
                            TextButton(onClick = {
                                showDialog = false
                                selectedHour = timeState.hour
                                selectedMinute = timeState.minute
                            }) {
                                Text(text = "ОК")
                            }
                        }
                    }
                }
            }

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
                            Text(
                                modifier = Modifier.clickable {
                                    showDialog = true
                                },
                                text = if ((selectedHour < 10) and (selectedMinute < 10)) "0$selectedHour:0$selectedMinute"
                                else if ((selectedHour >= 10) and (selectedMinute < 10)) "$selectedHour:0$selectedMinute"
                                else if ((selectedHour < 10) and (selectedMinute >= 10)) "0$selectedHour:$selectedMinute"
                                else "$selectedHour:$selectedMinute"
                            )
                        }
                    }
                }

                Button(
                    onClick = {
                        val time = if ((selectedHour < 10) and (selectedMinute < 10)) "0$selectedHour:0$selectedMinute"
                        else if ((selectedHour >= 10) and (selectedMinute < 10)) "$selectedHour:0$selectedMinute"
                        else if ((selectedHour < 10) and (selectedMinute >= 10)) "0$selectedHour:$selectedMinute"
                        else "$selectedHour:$selectedMinute"

                        profileViewModel.saveChanges(name, switchState, time)
                        onSaveButtonPressed()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .width(170.dp)
                ) {
                    Text(
                        color = Color.White,
                        text = "Сохранить",
                    )
                }

                Text(
                    modifier = Modifier
                        .padding(top = 5.dp)
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