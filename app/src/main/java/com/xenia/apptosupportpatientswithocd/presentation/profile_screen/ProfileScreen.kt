package com.xenia.apptosupportpatientswithocd.presentation.profile_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ProfileScreen() {

    var name by remember { mutableStateOf("Введите логин") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Ваше имя") },
            singleLine = true,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
                .background(Color.Gray),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Ежедневные напоминания")
            Switch(checked = true, onCheckedChange = {

            })
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
                .background(Color.Gray),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Время уведомлений")
            Text(text = "20:00")
        }

        Text(text = "Выйти")
        Text(text = "Удалить аккаунт")
    }

}