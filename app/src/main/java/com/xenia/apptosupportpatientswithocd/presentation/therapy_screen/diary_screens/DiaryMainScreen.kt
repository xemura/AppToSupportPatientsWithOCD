package com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.diary_screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.xenia.apptosupportpatientswithocd.presentation.composable.BarGraph
import com.xenia.apptosupportpatientswithocd.presentation.composable.BarType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryMainScreen(
    onBackPressed: () -> Unit,
    onEditPressed: () -> Unit,
    onAddPressed: () -> Unit,
) {

    val moodList = mutableListOf(
        Mood("05.04.24", 5, "ляляля 5"),
        Mood("04.04.24", 6, "ляля 6"),
        Mood("03.04.24", 9, "ляляля 9")
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
                    Text(text = "Дневник настроения")
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = contentPadding.calculateTopPadding()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column {
                    val dataList = mutableListOf(3,6,9,5,7)
                    val floatValue = mutableListOf<Float>()
                    val datesList = mutableListOf(
                        "01.04", "02.04",
                        "03.04", "04.04",
                        "05.04"
                    )

                    dataList.forEachIndexed { index, value ->
                        floatValue.add(index = index, element = value.toFloat()/dataList.max().toFloat())
                    }

                    BarGraph(
                        graphBarData = floatValue,
                        xAxisScaleData = datesList,
                        barData_ = dataList,
                        height = 300.dp,
                        roundType = BarType.TOP_CURVED,
                        barWidth = 20.dp,
                        barBrush = Brush.verticalGradient(
                            listOf(
                                Color(0xFF0575e6),
                                Color(0xFFb5e2fa)
                            )
                        ),
                        barArrangement = Arrangement.SpaceEvenly
                    )

                    Text(
                        modifier = Modifier.padding(horizontal = 30.dp, vertical = 5.dp),
                        text = "Ваше настроение по дням:"
                    )
                }

            }
            items(moodList) {
                Card(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 5.dp)
                        .clickable {
                            onEditPressed()
                            // просмотр записи о настроении - редактирование
                        },
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                ) {
                    Text(
                        modifier = Modifier
                            .padding(20.dp),
                        text = "${it.date}  ${it.moodAssessment}/10"
                    )
                }
            }
            item {
                Button(
                    onClick = { onAddPressed() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 5.dp),

                    ) {
                    Text(
                        color = Color.White,
                        text = "Добавить",
                    )
                }
            }
        }
    }

}