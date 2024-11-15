package com.xenia.apptosupportpatientswithocd.presentation.screens.therapy_screen

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
import androidx.compose.ui.res.stringResource
import com.xenia.apptosupportpatientswithocd.R
import com.xenia.apptosupportpatientswithocd.presentation.composable.CardTherapyMain
import com.xenia.apptosupportpatientswithocd.presentation.composable.topbar.TopBarWithoutArrowBack

@Composable
fun TherapyScreen(
    onDiaryMoodCardPress: () -> Unit,
    onHomeworkCardPress: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarWithoutArrowBack(topBarName = stringResource(R.string.therapy))
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
                stringResource(R.string.diary_mood),
                onCardPressed = {
                    onDiaryMoodCardPress()
                }
            )

            CardTherapyMain(
                stringResource(R.string.homework),
                onCardPressed = {
                    onHomeworkCardPress()
                }
            )
        }
    }
}