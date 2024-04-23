package com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.homework_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.xenia.apptosupportpatientswithocd.domain.entity.HomeworkModel
import com.xenia.apptosupportpatientswithocd.presentation.composable.TopBarWithArrowBack

@Composable
fun EditHomeworkScreen(
    homework: HomeworkModel,
    onBackPressed: () -> Unit,
    onSavePressed: (String, String, String, String) -> Unit
) {
    var obsessionInfo by remember { mutableStateOf(homework.obsessionInfo) }
    var triggerInfo by remember { mutableStateOf(homework.triggerInfo) }
    var adviceInfo by remember { mutableStateOf(homework.adviceInfo) }

    Scaffold(
        topBar = {
            TopBarWithArrowBack(topBarName = "Редактировать") {
                onBackPressed()
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(top = contentPadding.calculateTopPadding() + 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 5.dp),
                value = obsessionInfo,
                onValueChange = { obsessionInfo = it },
                placeholder = { Text(text = "Введите обсессию") },
                label = { Text("Обсессия") },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFF0575e6),
                    unfocusedIndicatorColor = Color.Black,
                    focusedLabelColor = Color(0xFF0575e6),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 5.dp),
                value = triggerInfo,
                onValueChange = { triggerInfo = it },
                placeholder = { Text(text = "Введите триггер") },
                label = { Text("Триггер") },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFF0575e6),
                    unfocusedIndicatorColor = Color.Black,
                    focusedLabelColor = Color(0xFF0575e6),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 5.dp),
                value = adviceInfo,
                onValueChange = { adviceInfo = it },
                placeholder = { Text(text = "Введите совет") },
                label = { Text("Совет") },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFF0575e6),
                    unfocusedIndicatorColor = Color.Black,
                    focusedLabelColor = Color(0xFF0575e6),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            )

            Button(
                onClick = {
                    onSavePressed(
                        homework.id,
                        obsessionInfo,
                        triggerInfo,
                        adviceInfo
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text(
                    color = Color.White,
                    text = "Сохранить",
                )
            }
        }
    }
}