package com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.practice_screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.xenia.apptosupportpatientswithocd.domain.entity.HomeworkModel
import com.xenia.apptosupportpatientswithocd.domain.entity.StatisticModel
import com.xenia.apptosupportpatientswithocd.presentation.composable.TopBarWithoutArrowBack

@Composable
fun PracticeContentScreen(
    homework: HomeworkModel,
    statisticModel: StatisticModel,
    onNextButtonPressed: (StatisticModel) -> Unit
) {
    Scaffold(
        topBar = {
            TopBarWithoutArrowBack(topBarName = "Практика")
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 80.dp),
                onClick = {
                    onNextButtonPressed(statisticModel)
                },
                shape = CircleShape,
                containerColor = Color(0xFF0575e6),
                contentColor = Color.White
            ) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, "Floating action button.")
            }
        }
    ) { contentPadding ->

        Column(
            modifier = Modifier.padding(top = contentPadding.calculateTopPadding() + 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth(),
                text = "Триггер",
                textAlign = TextAlign.Start
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
                Text(
                    modifier = Modifier
                        .padding(15.dp),
                    text = homework.triggerInfo
                )
            }

            Text(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth(),
                text = "Совет",
                textAlign = TextAlign.Start
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
                Text(
                    modifier = Modifier
                        .padding(15.dp),
                    text = homework.adviceInfo
                )
            }
        }
    }
}