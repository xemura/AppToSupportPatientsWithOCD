package com.xenia.apptosupportpatientswithocd.presentation.composable.login_password_fields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xenia.apptosupportpatientswithocd.R
import com.xenia.apptosupportpatientswithocd.ui.theme.ErrorColor


const val MAX_TEXT_LENGTH = 8

@Composable
fun PasswordField(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    var isError by rememberSaveable { mutableStateOf(false) }

    fun validate(text: String) {
        isError = text.length < MAX_TEXT_LENGTH
    }

    val icon =
        if (isVisible) painterResource(R.drawable.ic_visibility_on)
        else painterResource(R.drawable.ic_visibility_off)

    val visualTransformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        value = value,
        onValueChange = {
            onValueChange(it)
            validate(it)
        },
        placeholder = { Text(text = placeholder) },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock") },
        trailingIcon = {
            if (isError) {
                Icon(Icons.Filled.Info, stringResource(R.string.error), tint = ErrorColor)
            } else {
                IconButton(onClick = { isVisible = !isVisible }) {
                    Icon(painter = icon, contentDescription = "Visibility")
                }
            }
        },
        supportingText = {
            if (isError) {
                Text(
                    text = stringResource(R.string.password_error),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    fontSize = 12.sp
                )
            }
        },
        label = { Text(placeholder) },
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(10.dp)
    )
}