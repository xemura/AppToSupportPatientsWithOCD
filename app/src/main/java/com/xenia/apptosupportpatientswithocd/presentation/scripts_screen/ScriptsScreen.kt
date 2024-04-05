package com.xenia.apptosupportpatientswithocd.presentation.scripts_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.xenia.apptosupportpatientswithocd.presentation.composable.CardScript

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScriptsScreen(
    onFloatingActionButtonClick: () -> Unit
) {

    val list = listOf(
        ScriptModel(
            "Выйти из дома", true, listOf(
                Action("взять кошелек", true),
                Action("выключить всё из розеток", false),
                Action("взять ключи", false),
            )
        ),
        ScriptModel(
            "Мытье рук", false, listOf(
                Action("действие 1", false),
                Action("действие 2", true),
                Action("действие 3", true),
            )
        ),
        ScriptModel(
            "Навести порядок", false, listOf(
                Action("действие 1", true),
                Action("действие 2", false),
                Action("действие 3", true),
            )
        ),
        ScriptModel(
            "Работа", false, listOf(
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
            }
        }
    }
}