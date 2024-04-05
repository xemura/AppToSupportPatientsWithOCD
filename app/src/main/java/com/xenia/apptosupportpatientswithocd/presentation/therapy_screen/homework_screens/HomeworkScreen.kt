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
        HomeworkModel(
            "Страх подхватить инфекцию",
            "Прикосновения к рукам людей, коже, волосам, одежде, деньгам. Пользование туалетами вне дома, " +
                    "прикосновения к чужим животным. Обращение с предметами, к которым прикасался один или несколько человек.",
            "1. Каждый день ходить в местный супермаркет/магазин, брать деньги и приносить товары домой, не моя их и себя." +
                    "\n2. Принимать в гостях друзей/родственников, по крайней мере, один раз в неделю.\n" +
                    "3. Позволить своей семье выходить и заходить в дом без ежедневной стирки и уборки.\n",
        ),
        HomeworkModel(
            "Страх перед несовершенством",
            "Написание писем, эссе, записей. Отправка писем. Принимать душ (из-за бутылок, которые я использую). Одевание и раздевание. Приготовление пищи. Глажка.",
            "1. Написать эссе несимметричным способом менее чем за 2 часа, 3 раза в неделю.\n" +
                    "2. Принимать душ за 20 минут каждый день.\n" +
                    "3. Готовить еду для моих родителей за 45 минут 2 раза в неделю."),
        HomeworkModel("\"Плохие\" мысли о людях, которых я люблю", "", ""),
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
                onEditPressed()
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
                modifier = Modifier.padding(bottom = 5.dp, start = 5.dp),
                text = homeworkName.obsessionInfo
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = {
                        onPracticePressed()
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
                        onStatisticPressed()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = "Статистика",
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}
