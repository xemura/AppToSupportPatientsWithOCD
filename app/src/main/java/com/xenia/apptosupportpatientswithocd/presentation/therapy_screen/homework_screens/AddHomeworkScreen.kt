package com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.homework_screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import com.xenia.apptosupportpatientswithocd.presentation.composable.MyButtonSave
import com.xenia.apptosupportpatientswithocd.presentation.composable.MyOutlinedTextField
import com.xenia.apptosupportpatientswithocd.presentation.composable.topbar.TopBarWithArrowBack

@Composable
fun AddHomeworkScreen(
    onBackPressed: () -> Unit,
    onSavePressed: (String, String, String) -> Unit
) {
    var obsessionInfo by remember { mutableStateOf("") }
    var triggerInfo by remember { mutableStateOf("") }
    var adviceInfo by remember { mutableStateOf("") }

    var isErrorObsession by rememberSaveable { mutableStateOf(false) }
    var isErrorTrigger by rememberSaveable { mutableStateOf(false) }
    var isErrorInfo by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBarWithArrowBack(topBarName = "Создать задание") {
                onBackPressed()
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(top = contentPadding.calculateTopPadding() + 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            MyOutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 5.dp),
                value = obsessionInfo,
                onValueChange = {
                    obsessionInfo = it
                    isErrorObsession = it == ""
                },
                placeholderText = "Введите обсессию",
                labelText = "Обсессия",
                isError = isErrorObsession,
                supportingText = "это поле не должно быть пустым"
            )

            MyOutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 5.dp),
                value = triggerInfo,
                onValueChange = {
                    triggerInfo = it
                    isErrorTrigger = it == ""
                },
                placeholderText = "Введите триггер",
                labelText = "Триггер",
                isError = isErrorTrigger,
                supportingText = "это поле не должно быть пустым"
            )

            MyOutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 5.dp),
                value = adviceInfo,
                onValueChange = {
                    adviceInfo = it
                    isErrorInfo = it == ""
                },
                placeholderText = "Введите совет",
                labelText = "Совет",
                isError = isErrorInfo,
                supportingText = "это поле не должно быть пустым"
            )

            MyButtonSave(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .height(50.dp),
                onButtonClick = {
                    if ((obsessionInfo != "") and (triggerInfo != "") and (adviceInfo != "")) {
                        onSavePressed(obsessionInfo, triggerInfo, adviceInfo)
                    } else {
                        Toast.makeText(
                            context,
                            "Проверьте введенные данные!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                buttonText = "Сохранить"
            )
        }
    }
}
