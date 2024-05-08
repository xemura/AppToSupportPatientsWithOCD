package com.xenia.apptosupportpatientswithocd.presentation.composable.login_password_fields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xenia.apptosupportpatientswithocd.ui.theme.ErrorColor

@Composable
fun LoginField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    var isError by rememberSaveable { mutableStateOf(false) }

    fun validate(text: String) {
        isError = !text.contains("@")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        value = value,
        onValueChange = {
            onValueChange(it)
            validate(it)
        },
        trailingIcon = {
            if (isError)
                Icon(Icons.Filled.Info, "Error", tint = ErrorColor)
        },
        supportingText = {
            if (isError) {
                Text(
                    text = "формат логина: example@pochta.com",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    fontSize = 12.sp
                )
            }
        },
        placeholder = { Text(text = "Введите логин") },
        isError = isError,
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color(0xFF0575e6),
            unfocusedIndicatorColor = Color.Black,
            focusedLabelColor = Color(0xFF0575e6),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            errorContainerColor = Color.White,
            errorIndicatorColor = ErrorColor,
            errorLabelColor = ErrorColor
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