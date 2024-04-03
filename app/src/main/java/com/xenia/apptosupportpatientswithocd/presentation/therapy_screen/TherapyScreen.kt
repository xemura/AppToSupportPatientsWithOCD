package com.xenia.apptosupportpatientswithocd.presentation.therapy_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TherapyScreen(
    onDiaryMoodCardPress: () -> Unit,
    onHomeworkCardPress: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .shadow(
                        elevation = 5.dp,
                        spotColor = Color.DarkGray
                    ),
                title = {
                    Text(text = "Терапия")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    titleContentColor = Color.White,
                    containerColor = Color(0xFF101018)
                )
            )
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

            Card(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 5.dp)
                    .clickable {
                        onDiaryMoodCardPress()
                    },
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
            ) {
                Text(
                    modifier = Modifier
                        .padding(15.dp),
                    text = "Дневник настроения"
                )
            }

            Card(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 5.dp)
                    .clickable {
                        onHomeworkCardPress()
                    },
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
            ) {
                Text(
                    modifier = Modifier
                        .padding(15.dp),
                    text = "Домашняя работа"
                )
            }

        }
    }
}