package com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.diary_screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.xenia.apptosupportpatientswithocd.presentation.composable.MyOutlinedTextField
import com.xenia.apptosupportpatientswithocd.presentation.composable.topbar.TopBarWithArrowBack

@Composable
fun AddMoodScreen(
    onBackPressed: () -> Unit,
    onSavePressed: (Int, String) -> Unit,
) {
    var mood by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    var isErrorMood by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBarWithArrowBack(topBarName = "Текущее настроение") {
                onBackPressed()
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = contentPadding.calculateTopPadding() - contentPadding.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                text = "Введите оценку своего настроения",
                textAlign = TextAlign.Center
            )

            MyOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                value = mood,
                onValueChange = {
                    mood = it
                    isErrorMood = it == ""
                },
                placeholderText = "Введите оценку в виде числа",
                labelText = "Оценка настроения",
                isError = isErrorMood,
                supportingText = "это поле не должно быть пустым",
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            MyOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                value = note,
                onValueChange = {
                    note = it
                },
                placeholderText = "Заметка о настроении",
                labelText = "Заметка"
            )

            Button(
                onClick = {
                    if (mood != "") {
                        onSavePressed(mood.toInt(), note)
                    } else {
                        Toast.makeText(
                            context,
                            "Проверьте введенные данные!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp, vertical = 5.dp)
                    .height(50.dp),
                ) {
                Text(
                    color = Color.White,
                    text = "Сохранить",
                )
            }
        }
    }
}