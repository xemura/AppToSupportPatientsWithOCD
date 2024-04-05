package com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.homework_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xenia.apptosupportpatientswithocd.R
import com.xenia.apptosupportpatientswithocd.presentation.composable.BarGraph
import com.xenia.apptosupportpatientswithocd.presentation.composable.BarType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticHomeworkScreen(
    onBackPressed: () -> Unit
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
                    Text(text = "Статистика")
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
        Box(
            modifier = Modifier.padding(top = contentPadding.calculateTopPadding() + 5.dp)
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 5.dp),
                    text = "Статистика по домашней работе: Страх перед несовершенством"
                )

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
                    text = "График показывает оценки состояний после прохождения данного домашнего задания",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
    }
}