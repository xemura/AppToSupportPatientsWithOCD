package com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.homework_screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeworkScreen(
    onBackPressed: () -> Unit,
    onAddPressed: () -> Unit,
    onStatisticPressed: () -> Unit,
    onEditPressed: () -> Unit,
    onPracticePressed: () -> Unit,
) {

    val homeworksList = mutableListOf(
        HomeworkModel("Социальная деятельность", "", "", "", ""),
        HomeworkModel("Личные дела", "", "", "", ""),
        HomeworkModel("Дом", "", "", "", ""),
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .shadow(
                        elevation = 5.dp,
                        spotColor = Color.DarkGray
                    ),
                title = {
                    Text(text = "Домашние работы")
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
                .background(Color.White)
                .padding(top = contentPadding.calculateTopPadding() + 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Домашние задания")
                IconButton(onClick = { onAddPressed() }) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }

            LazyColumn {
                // тут еще смахиваем и удаляем домашнюю работу
                items(homeworksList) {
                    HomeworkCard(
                        homeworkName = it,
                        onStatisticPressed = { onStatisticPressed() },
                        onEditPressed = { onEditPressed() },
                        onPracticePressed = { onPracticePressed() }
                    )
                }
            }


        }
    }
}

@Composable
fun HomeworkCard(
    homeworkName: HomeworkModel,
    onStatisticPressed: () -> Unit,
    onEditPressed: () -> Unit,
    onPracticePressed: () -> Unit,
) {
    Card(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .clickable {
                onStatisticPressed()
                // статистика домашней работы (может лучше редактировать здесь)
            },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)

        ) {
            Text(
                modifier = Modifier.padding(bottom = 5.dp),
                text = homeworkName.homeworkName
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        onPracticePressed()
                        // страница практика
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = "Практика",
                        fontSize = 15.sp
                    )
                }
                Spacer(modifier = Modifier.padding(3.dp))
                Button(
                    onClick = {
                        onEditPressed()
                        // страницаа редактировать // а тут статистика ??
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = "Редактировать",
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}
