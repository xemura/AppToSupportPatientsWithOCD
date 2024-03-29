package com.xenia.apptosupportpatientswithocd.presentation.therapy_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TherapyScreen() {
    Text(
        modifier = Modifier.fillMaxSize().background(Color.White),
        textAlign = TextAlign.Center,
        text = "TherapyScreen",
        color = Color.Black
    )
}