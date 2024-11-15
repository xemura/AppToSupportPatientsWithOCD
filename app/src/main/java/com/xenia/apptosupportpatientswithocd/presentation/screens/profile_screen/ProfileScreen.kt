package com.xenia.apptosupportpatientswithocd.presentation.screens.profile_screen

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xenia.apptosupportpatientswithocd.R
import com.xenia.apptosupportpatientswithocd.domain.entity.UserModel
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.AuthViewModel
import com.xenia.apptosupportpatientswithocd.presentation.screens.auth_screen.SignInScreen
import com.xenia.apptosupportpatientswithocd.presentation.composable.GradientSwitch
import com.xenia.apptosupportpatientswithocd.presentation.composable.MyOutlinedTextField
import com.xenia.apptosupportpatientswithocd.presentation.composable.topbar.TopBarWithoutArrowBack
import com.xenia.apptosupportpatientswithocd.presentation.getApplicationComponent
import com.xenia.apptosupportpatientswithocd.presentation.states.ProfileScreenState
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.ProfileViewModel

@Composable
fun ProfileScreenContent(
    onSaveButtonPressed: (String, Boolean, String) -> Unit,
    onSignOutPressed: () -> Unit,
    viewModel: AuthViewModel,
) {
    val component = getApplicationComponent()
    val profileViewModel: ProfileViewModel = viewModel(factory = component.getViewModelFactory())

    val screenState = profileViewModel.screenState.collectAsState(ProfileScreenState.Initial)

    when (val currentState = screenState.value) {
        is ProfileScreenState.Profile -> {
            ProfileScreen(
                viewModel = viewModel,
                onSaveButtonPressed = { name, switchState, time ->
                    onSaveButtonPressed(name, switchState, time)
                },
                onSignOutPressed = { onSignOutPressed() },
                userInfo = currentState.userInfo
            )
        }

        ProfileScreenState.Initial -> {

        }

        ProfileScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.Black)
            }
        }

        else -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onSaveButtonPressed: (String, Boolean, String) -> Unit,
    onSignOutPressed: () -> Unit,
    viewModel: AuthViewModel,
    userInfo: UserModel
) {

    var name by remember { mutableStateOf(userInfo.name) }
    var screenLogin by remember { mutableStateOf(false) }
    var switchState by remember { mutableStateOf(userInfo.notificationEnable) }

    var showDialog by remember { mutableStateOf(false) }

    var selectedHour by remember { mutableIntStateOf(userInfo.notificationTime.take(2).toInt()) }
    var selectedMinute by remember { mutableIntStateOf(userInfo.notificationTime.drop(3).toInt()) }
    val timeState = rememberTimePickerState(
        initialHour = selectedHour,
        initialMinute = selectedMinute,
        true
    )

    var isError by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    fun validate(text: String) {
        isError = text == ""
    }

    if (screenLogin) {
        SignInScreen(viewModel = viewModel)
    } else {
        Scaffold(
            topBar = {
                TopBarWithoutArrowBack(stringResource(R.string.profile))
            }
        ) { contentPadding ->

            if (showDialog) {
                BasicAlertDialog(
                    onDismissRequest = { showDialog = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
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
                                timeSelectorSelectedContainerColor = Color(0xFFCFEEFF),
                                timeSelectorUnselectedContainerColor = Color(0xFFF1F1F1),
                                selectorColor = Color(0xFF0575e6),
                                clockDialColor = Color(0xFFF1F1F1),
                                containerColor = Color.White
                            )
                        )
                        Row(
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .fillMaxWidth(), horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(onClick = { showDialog = false }) {
                                Text(text = stringResource(R.string.cancel))
                            }
                            TextButton(onClick = {
                                showDialog = false
                                selectedHour = timeState.hour
                                selectedMinute = timeState.minute
                            }) {
                                Text(text = stringResource(R.string.ok))
                            }
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(
                        top = contentPadding.calculateTopPadding() + 20.dp,
                        bottom = contentPadding.calculateTopPadding() + 30.dp
                    ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    color = Color.Black,
                    text = stringResource(R.string.and_it_says_here_what_is_your_name),
                )

                MyOutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp)
                        .weight(0.5f, true),
                    value = name,
                    onValueChange = {
                        name = it
                        validate(it)
                    },
                    placeholderText = stringResource(R.string.enter_your_name),
                    labelText = stringResource(R.string.your_name),
                    isError = isError,
                    supportingText = stringResource(R.string.name_should_not_be_empty),
                    singleLine = true
                )

                Card(
                    modifier = Modifier
                        .padding(horizontal = 50.dp, vertical = 10.dp)
                        .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(10.dp))
                        .weight(1.25f, true),
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
                            Text(text = stringResource(R.string.daily_reminders))

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
                            Text(text = stringResource(R.string.notification_time))
                            Text(
                                modifier = Modifier.clickable {
                                    showDialog = true
                                },
                                text = getTime(selectedHour, selectedMinute)
                            )
                        }

                        Spacer(modifier = Modifier.padding(vertical = 5.dp))

                        Row(
                            modifier = Modifier
                                .padding(horizontal = 30.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // ???
                            Text(text = stringResource(R.string.configuring_the_notification_text))
                            Icon(
                                Icons.Filled.Add,
                                stringResource(R.string.floating_action_button),
                                modifier = Modifier.clickable { },
                            )
                        }
                    }
                }

                Card(
                    modifier = Modifier
                        .padding(start = 50.dp, top = 10.dp, end = 50.dp)
                        .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(10.dp))
                        .weight(1.7f, true),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                    ) {
                        item {
                            Text(
                                modifier = Modifier
                                    .padding(vertical = 10.dp),
                                text = stringResource(R.string.next_doctors_visit),
                                color = Color(0xFF0575e6)
                            )
                        }
                        item {
                            Card(
                                modifier = Modifier
                                    .border(
                                        BorderStroke(1.dp, Color(0xFF0575e6)),
                                        shape = RoundedCornerShape(10.dp)
                                    ),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFCFEEFF)
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(10.dp)
                                ) {
                                    Text(buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                color = Color.Black,
                                                fontWeight = FontWeight.ExtraBold
                                            )
                                        ) {
                                            append(stringResource(R.string.doctor))
                                        }
                                        append(stringResource(R.string.example_doctor))
                                    })
                                    Text(buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                color = Color.Black,
                                                fontWeight = FontWeight.ExtraBold
                                            )
                                        ) {
                                            append(stringResource(R.string.date_and_time))
                                        }
                                        append(stringResource(R.string.example_date_and_time))
                                    })
                                    Text(buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                color = Color.Black,
                                                fontWeight = FontWeight.ExtraBold
                                            )
                                        ) {
                                            append(stringResource(R.string.address))
                                        }
                                        append(stringResource(R.string.example_address))
                                    })
                                }
                            }
                        }
                        item {
                            Text(
                                modifier = Modifier
                                    .padding(vertical = 10.dp),
                                text = stringResource(R.string.last_visits),
                                color = Color.Gray
                            )
                        }
                        item {
                            Card(
                                modifier = Modifier
                                    .padding(bottom = 10.dp)
                                    .border(
                                        BorderStroke(1.dp, Color.Black),
                                        shape = RoundedCornerShape(10.dp)
                                    ),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(10.dp)
                                ) {
                                    Text(text = stringResource(R.string.doctor_example_full))
                                    Text(text = stringResource(R.string.date_and_time_full))
                                    Text(text = stringResource(R.string.address_example_full))
                                }
                            }
                        }
                        item {
                            Card(
                                modifier = Modifier
                                    .padding(bottom = 10.dp)
                                    .border(
                                        BorderStroke(1.dp, Color.Black),
                                        shape = RoundedCornerShape(10.dp)
                                    ),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(10.dp)
                                ) {
                                    Text(text = stringResource(R.string.doctor_example_full))
                                    Text(text = stringResource(R.string.date_and_time_full))
                                    Text(text = stringResource(R.string.address_example_full))
                                }
                            }
                        }
                        item {
                            Card(
                                modifier = Modifier
                                    .padding(bottom = 10.dp)
                                    .border(
                                        BorderStroke(1.dp, Color.Black),
                                        shape = RoundedCornerShape(10.dp)
                                    ),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(10.dp)
                                ) {
                                    Text(text = stringResource(R.string.doctor_example_full))
                                    Text(text = stringResource(R.string.date_and_time_full))
                                    Text(text = stringResource(R.string.address_example_full))
                                }
                            }
                        }
                    }
                }

                Button(
                    onClick = {
                        if (name != "") {
                            onSaveButtonPressed(
                                name,
                                switchState,
                                getTime(selectedHour, selectedMinute)
                            )
                        } else {
                            Toast.makeText(
                                context,
                                context.getString(R.string.check_entered_data),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .width(170.dp)
                        .padding(top = 10.dp)
                        .weight(0.3f, true)
                ) {
                    Text(
                        color = Color.White,
                        text = stringResource(R.string.save),
                    )
                }

                Text(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .clickable {
                            onSignOutPressed()
                            screenLogin = true
                        }
                        .alpha(0.7f)
                        .weight(0.2f, true),
                    text = stringResource(R.string.exit),
                    color = Color.Red
                )
            }
        }
    }
}

private fun getTime(selectedHour: Int, selectedMinute: Int): String {
    return if ((selectedHour < 10) and (selectedMinute < 10)) "0$selectedHour:0$selectedMinute"
    else if ((selectedHour >= 10) and (selectedMinute < 10)) "$selectedHour:0$selectedMinute"
    else if ((selectedHour < 10) and (selectedMinute >= 10)) "0$selectedHour:$selectedMinute"
    else "$selectedHour:$selectedMinute"
}