package com.xenia.apptosupportpatientswithocd.presentation.screens.modules_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.xenia.apptosupportpatientswithocd.R
import com.xenia.apptosupportpatientswithocd.presentation.composable.topbar.TopBarWithArrowBack

@Composable
fun ContentTextScreen(
    onBackPressed: () -> Unit,
    text: String,
) {
    Scaffold(
        topBar = {
            TopBarWithArrowBack(
                topBarName = stringResource(R.string.content),
                onBackPressed = { onBackPressed() }
            )
        }
    ) { contentPadding ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = contentPadding.calculateTopPadding() + 10.dp,
                    bottom = contentPadding.calculateTopPadding() + 30.dp,
                    start = 10.dp,
                    end = 10.dp
                ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.Black),
        ) {
            LazyColumn {
                item {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 10.dp),
                        text = text
                    )
                }
            }
        }
    }
}