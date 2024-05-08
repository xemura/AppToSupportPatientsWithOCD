package com.xenia.apptosupportpatientswithocd.presentation.scripts_screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xenia.apptosupportpatientswithocd.domain.entity.Action
import com.xenia.apptosupportpatientswithocd.presentation.composable.topbar.TopBarWithArrowBack
import com.xenia.apptosupportpatientswithocd.ui.theme.ErrorColor

@Composable
fun AddScriptScreen(
    onBackPressed: () -> Unit,
    onAddPressed: (String, List<Action>) -> Unit
) {

    val openAlertDialog = remember { mutableStateOf(false) }

    var scriptName by remember { mutableStateOf("") }
    var actionText by remember { mutableStateOf("") }
    val actionsList = remember { mutableListOf<Action>() }

    var isErrorScript by rememberSaveable { mutableStateOf(false) }
    var isErrorAction by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    fun validate(text: String) {
        isErrorScript = text == ""
    }

    fun validateAction(text: String) {
        isErrorAction = text == ""
    }

    when {
        openAlertDialog.value -> {
            AlertDialogExample(
                onDismissRequest = {
                    openAlertDialog.value = false
                    actionText = ""
                },
                onConfirmation = {
                    if (actionText != "") {
                        openAlertDialog.value = false
                        actionsList.add(Action("", actionText, false))
                        actionText = ""
                    } else {
                        Toast.makeText(
                            context,
                            "Введите название действия!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                dialogTitle = "Введите действие",
                dialogText = {
                    OutlinedTextField(
                        value = actionText,
                        onValueChange = {
                            actionText = it
                            validateAction(it)
                        },
                        placeholder = { Text(text = "Введите действие") },
                        singleLine = true,
                        isError = isErrorAction,
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color(0xFF0575e6),
                            unfocusedIndicatorColor = Color.Black,
                            focusedLabelColor = Color(0xFF0575e6),
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            errorContainerColor = Color.White,
                            errorIndicatorColor = ErrorColor,
                            errorLabelColor = ErrorColor
                        ),
                        supportingText = {
                            if (isErrorAction) {
                                Text(
                                    text = "название действия не должно быть пустым",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.End,
                                    fontSize = 12.sp
                                )
                            }
                        },
                        label = { Text("Действие") },
                    )
                }
            )
        }
    }

    Scaffold(
        topBar = {
            TopBarWithArrowBack(
                topBarName = "Создать сценарий"
            ) {
                onBackPressed()
            }
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
                value = scriptName,
                onValueChange = {
                    scriptName = it
                    validate(it)
                },
                placeholder = { Text(text = "Введите название сценария") },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFF0575e6),
                    unfocusedIndicatorColor = Color.Black,
                    focusedLabelColor = Color(0xFF0575e6),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    errorContainerColor = Color.White,
                    errorIndicatorColor = ErrorColor,
                    errorLabelColor = ErrorColor
                ),
                isError = isErrorScript,
                supportingText = {
                    if (isErrorScript) {
                        Text(
                            text = "название сценария не должно быть пустым",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                            fontSize = 12.sp
                        )
                    }
                },
                label = { Text("Название сценария") },
                shape = RoundedCornerShape(10.dp)
            )

            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = "Список действий"
            )

            Card(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 5.dp),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
            ) {
                LazyColumn {
                    items(actionsList) {
                        Text(modifier = Modifier.padding(10.dp), text = it.actionText)
                    }
                    item {
                        val modifier = if (actionsList.isEmpty()) {
                            Modifier.padding(10.dp)
                        } else Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp)

                        Button(
                            onClick = { openAlertDialog.value = true },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                            shape = RoundedCornerShape(8.dp),
                            modifier = modifier
                                .fillMaxWidth()
                        ) {
                            Text(color = Color.White, text = "Добавить")
                        }
                    }
                }
            }

            Button(
                onClick = {
                    if (scriptName != "") {
                        onAddPressed(scriptName, actionsList)
                    } else {
                        Toast.makeText(
                            context,
                            "Введите название сценария!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Text(color = Color.White, text = "Сохранить")
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
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = { onConfirmation() }) { Text("OK") }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) { Text("Отмена") }
        }
    )
}
