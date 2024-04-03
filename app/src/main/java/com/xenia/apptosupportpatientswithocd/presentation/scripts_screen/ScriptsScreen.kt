package com.xenia.apptosupportpatientswithocd.presentation.scripts_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.xenia.apptosupportpatientswithocd.presentation.composable.CardScript

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScriptsScreen(
    onFloatingActionButtonClick: () -> Unit
) {

    var mExpanded by remember { mutableStateOf(false) }
    val mCities = listOf("Delhi", "Mumbai", "Chennai", "Kolkata", "Hyderabad", "Bengaluru", "Pune")
    var mSelectedText by remember { mutableStateOf("") }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    var checkBoxState by remember { mutableStateOf(false) }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val list = listOf(
        ScriptModel(
            "Сценарий 1", true, listOf(
                Action("действие 1", true),
                Action("действие 2", false),
                Action("действие 3", false),
            )
        ),
        ScriptModel(
            "Сценарий 2", true, listOf(
                Action("действие 1", false),
                Action("действие 2", true),
                Action("действие 3", true),
            )
        ),
        ScriptModel(
            "Сценарий 3", false, listOf(
                Action("действие 1", true),
                Action("действие 2", false),
                Action("действие 3", true),
            )
        ),
        ScriptModel(
            "Сценарий 4", false, listOf(
                Action("действие 1", true),
                Action("действие 2", true),
                Action("действие 3", false),
            )
        )
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .shadow(
                        elevation = 5.dp,
                        spotColor = Color.DarkGray
                    ),
                title = {
                    Text(text = "Ритуалы")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    titleContentColor = Color.White,
                    containerColor = Color(0xFF101018)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 80.dp),
                onClick = {
                    onFloatingActionButtonClick()
                },
                shape = CircleShape,
                containerColor = Color(0xFF0575e6),
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = contentPadding.calculateTopPadding()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(list) {
                CardScript(scriptModel = it)
//                OutlinedTextField(
//                    value = mSelectedText,
//                    onValueChange = { mSelectedText = it },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .onGloballyPositioned { coordinates ->
//                            mTextFieldSize = coordinates.size.toSize()
//                        },
//                    label = { Text(it.name) },
//                    trailingIcon = {
//                        Icon(icon, "contentDescription",
//                            Modifier.clickable { mExpanded = !mExpanded })
//                    }
//                )
//
//                DropdownMenu(
//                    expanded = mExpanded,
//                    onDismissRequest = { mExpanded = false },
//                    modifier = Modifier
//                        .background(Color.White)
//                        .fillMaxWidth()
//                        .padding(horizontal = 20.dp)
//                        .clip(RoundedCornerShape(10.dp))
//                        .border(
//                            BorderStroke(width = 1.dp, color = Color.Black),
//                            shape = RoundedCornerShape(10.dp)
//                        )
//
//                ) {
//                    it.listActions.forEach { action ->
//                        DropdownMenuItem(
//                            leadingIcon = {
//                                Checkbox(
//                                    modifier = Modifier.clip(RoundedCornerShape(10.dp)),
//                                    checked = action.checkBoxState,
//                                    onCheckedChange = {
//                                        checkBoxState = !checkBoxState
//                                    })
//                            },
//                            text = {
//                                Text(
//                                    modifier = Modifier.fillMaxHeight(),
//                                    text = action.name
//                                )
//                            },
//                            onClick = {
//                                mSelectedText = action.name
//                                mExpanded = false
//                            })
//                    }
//                }
            }
        }
    }
}