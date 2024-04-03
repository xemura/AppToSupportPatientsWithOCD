package com.xenia.apptosupportpatientswithocd.presentation.scripts_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScriptScreen(
    onBackPressed: () -> Unit,
    onAddPressed: () -> Unit
) {

    val openAlertDialog = remember { mutableStateOf(false) }
    var nameText by remember { mutableStateOf("") }

    val actionsList = remember { mutableStateOf<List<Action>>(mutableListOf()) }

    var actionText by remember { mutableStateOf("") }

    val list = remember { mutableListOf<Action>() }

    when {
        openAlertDialog.value -> {
            AlertDialogExample(
                onDismissRequest = {
                    openAlertDialog.value = false
                    actionText = ""
                },
                onConfirmation = {
                    openAlertDialog.value = false
                    list.add(Action(actionText, false))
                    actionsList.value = list
                    actionText = ""
                },
                dialogTitle = "Введите действие",
                dialogText = {
                    OutlinedTextField(
                        value = actionText,
                        onValueChange = {
                            actionText = it
                        },
                        placeholder = { Text(text = "Введите действие") },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color(0xFF0575e6),
                            unfocusedIndicatorColor = Color.Black,
                            focusedLabelColor = Color(0xFF0575e6),
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        label = { Text("Действие") },
                    )
                }
            )
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .shadow(
                        elevation = 5.dp,
                        spotColor = Color.DarkGray
                    ),
                title = {
                    Text(text = "Создать сценарий")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    titleContentColor = Color.White,
                    containerColor = Color(0xFF101018)
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        onBackPressed()
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(top = contentPadding.calculateTopPadding()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 5.dp),
                value = nameText,
                onValueChange = { nameText = it },
                placeholder = { Text(text = "Введите название сценария") },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFF0575e6),
                    unfocusedIndicatorColor = Color.Black,
                    focusedLabelColor = Color(0xFF0575e6),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                label = { Text("Название сценария") },
                shape = RoundedCornerShape(10.dp)
            )

            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = "Список действий"
            )

            if (actionsList.value.isEmpty()) {
                Card(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 30.dp, vertical = 5.dp)
                        .clickable {
                            openAlertDialog.value = true
                        },
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                ) {

                }
            } else {
                Card(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 5.dp)
                        .clickable {
                            openAlertDialog.value = true
                        },
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                ) {
                    LazyColumn {
                        items(actionsList.value) {
                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = it.name
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Button(
                onClick = { onAddPressed() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Text(
                    color = Color.White,
                    text = "Сохранить",
                )
            }

        }
    }
}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: @Composable () -> Unit,
) {
    AlertDialog(
        containerColor = Color.White,
        title = {
            Text(text = dialogTitle)
        },
        text = {
            dialogText()
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Отмена")
            }
        }
    )
}
