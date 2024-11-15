package com.xenia.apptosupportpatientswithocd.presentation.composable

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyButtonSave(
    modifier: Modifier,
    onButtonClick: () -> Unit,
    buttonText: String,
) {
    Button(
        onClick = {
            onButtonClick()
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier,
    ) {
        Text(
            color = Color.White,
            text = buttonText,
        )
    }
}