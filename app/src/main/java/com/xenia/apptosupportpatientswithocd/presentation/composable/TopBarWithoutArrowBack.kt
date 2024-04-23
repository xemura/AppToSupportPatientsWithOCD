package com.xenia.apptosupportpatientswithocd.presentation.composable

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithoutArrowBack(topBarName: String) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .shadow(
                elevation = 5.dp,
                spotColor = Color.DarkGray
            ),
        title = {
            Text(text = topBarName)
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            titleContentColor = Color.White,
            containerColor = Color(0xFF101018)
        )
    )
}