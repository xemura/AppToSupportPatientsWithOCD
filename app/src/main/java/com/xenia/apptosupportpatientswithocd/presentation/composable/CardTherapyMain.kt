package com.xenia.apptosupportpatientswithocd.presentation.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CardTherapyMain(
    cardText: String,
    onCardPressed: () -> Unit
) {
    Card(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 5.dp)
            .clickable {
                onCardPressed()
            },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Text(
            modifier = Modifier
                .padding(15.dp),
            text = cardText
        )
    }
}