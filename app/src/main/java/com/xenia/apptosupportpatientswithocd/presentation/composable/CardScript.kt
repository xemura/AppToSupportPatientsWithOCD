package com.xenia.apptosupportpatientswithocd.presentation.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.xenia.apptosupportpatientswithocd.presentation.scripts_screen.ScriptModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardScript(scriptModel: ScriptModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(20.dp))
            .background(Color.White),
        shape = RoundedCornerShape(20.dp),
        onClick = { }
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
        ) {
            val modifier = if (scriptModel.dropDownBoxEnabled)
                Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 5.dp) else Modifier.padding(20.dp)

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = scriptModel.name)
                Icon(
                    imageVector = if (scriptModel.dropDownBoxEnabled) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.Black
                )
            }

            if (scriptModel.dropDownBoxEnabled) {
                scriptModel.listActions.forEach {
                    RoundedCornerCheckbox(
                        label = it.name,
                        isChecked = it.checkBoxState,
                        onValueChange = { },
                        modifier = Modifier.padding(start = 22.dp)
                    )
                }
            }
        }
    }
}