package com.xenia.apptosupportpatientswithocd.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoginField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = { Text(text = "Введите логин") },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color(0xFF0575e6),
            unfocusedIndicatorColor = Color.Black,
            focusedLabelColor = Color(0xFF0575e6),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        label = { Text("Логин") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email"
            )
        },
        shape = RoundedCornerShape(10.dp)
    )
}