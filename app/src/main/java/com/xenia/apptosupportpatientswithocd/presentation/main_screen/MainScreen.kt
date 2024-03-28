package com.xenia.apptosupportpatientswithocd.presentation.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun MainScreen(
    paddingValues: PaddingValues,
) {

    Text(
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center,
        text = "MainScreen",
        color = Color.Black
    )

    LazyColumn(
        contentPadding = paddingValues
    ) {

    }
}