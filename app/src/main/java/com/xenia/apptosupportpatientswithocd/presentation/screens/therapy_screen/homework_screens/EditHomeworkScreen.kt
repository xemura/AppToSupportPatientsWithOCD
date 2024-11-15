package com.xenia.apptosupportpatientswithocd.presentation.screens.therapy_screen.homework_screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.xenia.apptosupportpatientswithocd.R
import com.xenia.apptosupportpatientswithocd.domain.entity.HomeworkModel
import com.xenia.apptosupportpatientswithocd.presentation.composable.MyButtonSave
import com.xenia.apptosupportpatientswithocd.presentation.composable.MyOutlinedTextField
import com.xenia.apptosupportpatientswithocd.presentation.composable.topbar.TopBarWithArrowBack

@Composable
fun EditHomeworkScreen(
    homework: HomeworkModel,
    onBackPressed: () -> Unit,
    onSavePressed: (String, String, String, String) -> Unit
) {
    var obsessionInfo by remember { mutableStateOf(homework.obsessionInfo) }
    var triggerInfo by remember { mutableStateOf(homework.triggerInfo) }
    var adviceInfo by remember { mutableStateOf(homework.adviceInfo) }

    var isErrorObsession by rememberSaveable { mutableStateOf(false) }
    var isErrorTrigger by rememberSaveable { mutableStateOf(false) }
    var isErrorInfo by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBarWithArrowBack(topBarName = stringResource(R.string.edit)) {
                onBackPressed()
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(top = contentPadding.calculateTopPadding() + 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyOutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 5.dp),
                value = obsessionInfo,
                onValueChange = {
                    obsessionInfo = it
                    isErrorObsession = it == ""
                },
                placeholderText = stringResource(R.string.enter_obsession),
                labelText = stringResource(R.string.obsession),
                isError = isErrorObsession,
                supportingText = stringResource(R.string.field_should_not_be_empty)
            )

            MyOutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 5.dp),
                value = triggerInfo,
                onValueChange = {
                    triggerInfo = it
                    isErrorTrigger = it == ""
                },
                placeholderText = stringResource(R.string.enter_trigger),
                labelText = stringResource(R.string.trigger),
                isError = isErrorTrigger,
                supportingText = stringResource(R.string.field_should_not_be_empty)
            )

            MyOutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 5.dp),
                value = adviceInfo,
                onValueChange = {
                    adviceInfo = it
                    isErrorInfo = it == ""
                },
                placeholderText = stringResource(R.string.enter_advice),
                labelText = stringResource(R.string.advice),
                isError = isErrorInfo,
                supportingText = stringResource(R.string.field_should_not_be_empty)
            )

            MyButtonSave(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .height(50.dp),
                onButtonClick = {
                    if ((obsessionInfo != "") and (triggerInfo != "") and (adviceInfo != "")) {
                        onSavePressed(homework.id, obsessionInfo, triggerInfo, adviceInfo)
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