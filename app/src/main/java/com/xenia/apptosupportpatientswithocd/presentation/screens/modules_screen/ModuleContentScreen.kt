package com.xenia.apptosupportpatientswithocd.presentation.screens.modules_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.xenia.apptosupportpatientswithocd.R
import com.xenia.apptosupportpatientswithocd.domain.entity.ModuleContentModel
import com.xenia.apptosupportpatientswithocd.presentation.composable.topbar.TopBarWithArrowBack

@Composable
fun ModuleContentScreen(
    onBackPressed: () -> Unit,
    contentList: List<ModuleContentModel>,
    onArticleClickListener: (String) -> Unit
) {

    Scaffold(
        topBar = {
            TopBarWithArrowBack(
                topBarName = stringResource(R.string.content_text),
                onBackPressed = { onBackPressed() }
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = contentPadding.calculateTopPadding()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            items(contentList) {
                Card(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 5.dp)
                        .clickable {
                            onArticleClickListener(it.textArticle)
                        },
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                ) {
                    Text(
                        modifier = Modifier
                            .padding(20.dp),
                        text = it.name
                    )
                }
            }
        }
    }
}