package com.xenia.apptosupportpatientswithocd.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xenia.apptosupportpatientswithocd.R
import com.xenia.apptosupportpatientswithocd.ui.theme.ErrorColor

@Composable
fun MyOutlinedTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    labelText: String,
    isError: Boolean = false,
    supportingText: String = stringResource(R.string.field_should_not_be_empty),
    singleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = { Text(text = placeholderText) },
        label = { Text(text = labelText) },
        isError = isError,
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
        supportingText = {
            if (isError) {
                Text(
                    text = supportingText,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    fontSize = 12.sp
                )
            }
        },
        singleLine = singleLine,
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = keyboardOptions
    )
}