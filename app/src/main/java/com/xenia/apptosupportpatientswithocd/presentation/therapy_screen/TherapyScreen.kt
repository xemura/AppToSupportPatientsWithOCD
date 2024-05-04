package com.xenia.apptosupportpatientswithocd.presentation.therapy_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.xenia.apptosupportpatientswithocd.presentation.composable.CardTherapyMain
import com.xenia.apptosupportpatientswithocd.presentation.composable.topbar.TopBarWithoutArrowBack

@Composable
fun TherapyScreen(
    onDiaryMoodCardPress: () -> Unit,
    onHomeworkCardPress: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarWithoutArrowBack(topBarName = "Терапия")
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = contentPadding.calculateTopPadding()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardTherapyMain(
                "Дневник настроения",
                onCardPressed = {
                    onDiaryMoodCardPress()
                }
            )

            CardTherapyMain(
                "Домашняя работа",
                onCardPressed = {
                    onHomeworkCardPress()
                }
            )
        }
    }
}