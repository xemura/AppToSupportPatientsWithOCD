package com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.practice_screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeContentScreen(
    onNextButtonPressed: () -> Unit
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
                    Text(text = "Практика")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    titleContentColor = Color.White,
                    containerColor = Color(0xFF101018)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 80.dp),
                onClick = {
                    onNextButtonPressed()
                },
                shape = CircleShape,
                containerColor = Color(0xFF0575e6),
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.KeyboardArrowRight, "Floating action button.")
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
                    .padding(horizontal = 30.dp).fillMaxWidth(),
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
                    text = "Прикосновения к рукам людей, коже, волосам, одежде, деньгам. Пользование туалетами вне дома, " +
                            "прикосновения к чужим животным. Обращение с предметами, к которым прикасался один или несколько человек."
                )
            }

            Text(
                modifier = Modifier
                    .padding(horizontal = 30.dp).fillMaxWidth(),
                text = "Совет",
                textAlign = TextAlign.Start
            )
            // еще чет надо наверное
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
                    text = "1. Каждый день ходить в местный супермаркет/магазин, брать деньги и приносить товары домой, не моя их и себя." +
                            "\n2. Принимать в гостях друзей/родственников, по крайней мере, один раз в неделю.\n" +
                            "3. Позволить своей семье выходить и заходить в дом без ежедневной стирки и уборки.\n"
                )
            }
        }
    }
}