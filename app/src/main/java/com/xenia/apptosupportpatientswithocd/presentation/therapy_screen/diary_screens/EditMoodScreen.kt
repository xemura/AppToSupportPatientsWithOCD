package com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.diary_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.xenia.apptosupportpatientswithocd.domain.entity.MoodModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditMoodScreen(
    mood: MoodModel,
    onBackPressed: () -> Unit,
    onSavePressed: (String, Int, String) -> Unit,
) {
    var moodText by remember { mutableStateOf("${mood.assessment}") }
    var note by remember { mutableStateOf(mood.note) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .shadow(
                        elevation = 5.dp,
                        spotColor = Color.DarkGray
                    ),
                title = {
                    Text(text = "Запись о настроении")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    titleContentColor = Color.White,
                    containerColor = Color(0xFF101018)
                ),
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
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
                text = "Дата записи: ${mood.time}",
                textAlign = TextAlign.Center
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                value = moodText,
                onValueChange = { moodText = it },
                placeholder = { Text(text = "Введите оценку в виде числа") },
                label = { Text("Оценка настроения") },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFF0575e6),
                    unfocusedIndicatorColor = Color.Black,
                    focusedLabelColor = Color(0xFF0575e6),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                value = note,
                onValueChange = { note = it },
                placeholder = { Text(text = "Заметка о настроении") },
                label = { Text("Заметка") },
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
                    onSavePressed(mood.id, moodText.toInt(), note)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp, vertical = 5.dp),

                ) {
                Text(
                    color = Color.White,
                    text = "Сохранить",
                )
            }
        }
    }
}