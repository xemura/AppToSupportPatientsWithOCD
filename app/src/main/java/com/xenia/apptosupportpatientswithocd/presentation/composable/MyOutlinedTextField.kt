package com.xenia.apptosupportpatientswithocd.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    labelText: String,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp),
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = { Text(text = placeholderText) },
        label = { Text(text = labelText) },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color(0xFF0575e6),
            unfocusedIndicatorColor = Color.Black,
            focusedLabelColor = Color(0xFF0575e6),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp)
    )
}