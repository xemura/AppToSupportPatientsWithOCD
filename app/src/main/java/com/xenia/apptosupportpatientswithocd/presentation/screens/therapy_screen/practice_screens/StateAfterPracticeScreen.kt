package com.xenia.apptosupportpatientswithocd.presentation.screens.therapy_screen.practice_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xenia.apptosupportpatientswithocd.R
import com.xenia.apptosupportpatientswithocd.domain.entity.StatisticModel
import com.xenia.apptosupportpatientswithocd.presentation.composable.CustomSlider
import com.xenia.apptosupportpatientswithocd.presentation.composable.CustomSliderDefaults
import com.xenia.apptosupportpatientswithocd.presentation.composable.topbar.TopBarWithoutArrowBack
import com.xenia.apptosupportpatientswithocd.presentation.composable.progress
import com.xenia.apptosupportpatientswithocd.presentation.composable.track
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StateAfterPracticeScreen(
    statisticModel: StatisticModel,
    onNextButtonPressed: (StatisticModel) -> Unit
) {
    var sliderValue by remember { mutableFloatStateOf(0f) }

    Scaffold(
        topBar = {
            TopBarWithoutArrowBack(topBarName = stringResource(R.string.practice))
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 80.dp),
                onClick = {
                    onNextButtonPressed(
                        StatisticModel(
                            statisticBeforePractice = statisticModel.statisticBeforePractice,
                            statisticAfterPractice = sliderValue.roundToInt(),
                            homeworkID = statisticModel.homeworkID
                        )
                    )
                },
                shape = CircleShape,
                containerColor = Color(0xFF0575e6),
                contentColor = Color.White
            ) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, stringResource(R.string.floating_action_button))
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = contentPadding.calculateTopPadding() + 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                text = stringResource(R.string.text_practice),
                textAlign = TextAlign.Start,
                color = Color(0xFF0575e6),
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                text = stringResource(R.string.score_10_practice),
                textAlign = TextAlign.Start,
                color = Color.Gray,
                fontSize = 14.sp
            )

            CustomSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                value = sliderValue,
                onValueChange = {
                    sliderValue = it
                },
                valueRange = 0f..10f,
                gap = 1,
                showIndicator = true,
                thumb = { thumbValue ->
                    CustomSliderDefaults.Thumb(
                        thumbValue = "$thumbValue",
                        color = Color.Transparent,
                        size = 40.dp,
                        modifier = Modifier.background(
                            brush = Brush.linearGradient(listOf(Color.Cyan, Color.Blue)),
                            shape = CircleShape
                        )
                    )
                },
                track = { sliderState ->
                    Box(
                        modifier = Modifier
                            .track()
                            .border(
                                width = 1.dp,
                                color = Color.LightGray.copy(alpha = 0.4f),
                                shape = CircleShape
                            )
                            .background(Color.White)
                            .padding(3.5.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Box(
                            modifier = Modifier
                                .progress(sliderPositions = sliderState)
                                .background(
                                    brush = Brush.horizontalGradient(
                                        listOf(
                                            Color(0xFF0575e6),
                                            Color(0xFFb5e2fa)
                                        )
                                    )
                                )
                        )
                    }
                }
            )
        }
    }
}