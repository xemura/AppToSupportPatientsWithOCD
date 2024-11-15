package com.xenia.apptosupportpatientswithocd.presentation.screens.therapy_screen.diary_screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.xenia.apptosupportpatientswithocd.R
import com.xenia.apptosupportpatientswithocd.presentation.composable.MyButtonSave
import com.xenia.apptosupportpatientswithocd.presentation.composable.MyOutlinedTextField
import com.xenia.apptosupportpatientswithocd.presentation.composable.topbar.TopBarWithArrowBack

@Composable
fun AddMoodScreen(
    onBackPressed: () -> Unit,
    onSavePressed: (Int, String) -> Unit,
) {
    var mood by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    var isErrorMood by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBarWithArrowBack(topBarName = stringResource(R.string.current_mood)) {
                onBackPressed()
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = contentPadding.calculateTopPadding() - contentPadding.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                text = stringResource(R.string.enter_assessment_mood),
                textAlign = TextAlign.Center
            )

            MyOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                value = mood,
                onValueChange = {
                    mood = it
                    isErrorMood = it == ""
                },
                placeholderText = stringResource(R.string.enter_score_number),
                labelText = stringResource(R.string.mood_assessment),
                isError = isErrorMood,
                supportingText = stringResource(R.string.field_should_not_be_empty),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            MyOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                value = note,
                onValueChange = {
                    note = it
                },
                placeholderText = stringResource(R.string.note_mood),
                labelText = stringResource(R.string.note)
            )

            MyButtonSave(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp, vertical = 5.dp)
                    .height(50.dp),
                onButtonClick = {
                    if (mood != "") {
                        onSavePressed(mood.toInt(), note)
                    } else {
                        Toast.makeText(
                            context,
                            context.getString(R.string.check_entered_data),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                buttonText = stringResource(R.string.save)
            )
        }
    }
}