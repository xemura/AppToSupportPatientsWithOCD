package com.xenia.apptosupportpatientswithocd.presentation.screens.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xenia.apptosupportpatientswithocd.R
import com.xenia.apptosupportpatientswithocd.presentation.composable.topbar.TopBarWithoutArrowBack

@Composable
fun MainScreen(
    contentPaddingValue: PaddingValues,
    onTherapyButtonPressed: () -> Unit,
    onModulesButtonPressed: () -> Unit,
    onScriptsButtonPressed: () -> Unit,
) {

    Scaffold(
        topBar = {
            TopBarWithoutArrowBack(topBarName = stringResource(R.string.main))
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = contentPadding.calculateTopPadding()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 10.dp)
                    .height(300.dp)
                    .clip(shape = RoundedCornerShape(10.dp)),
                painter = painterResource(id = R.drawable.main_screen_1),
                contentDescription = stringResource(R.string.picture_main_screen),
                contentScale = ContentScale.FillWidth
            )

            Text(
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 5.dp),
                color = Color.Black,
                fontSize = 20.sp,
                text = stringResource(R.string.main_screen_text),
                textAlign = TextAlign.Center
            )

            Button(
                onClick = { onTherapyButtonPressed() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Text(
                    color = Color.White,
                    text = stringResource(R.string.therapy),
                )
            }

            Button(
                onClick = { onModulesButtonPressed() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Text(
                    color = Color.White,
                    text = stringResource(R.string.modules),
                )
            }

            Button(
                onClick = { onScriptsButtonPressed() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Text(
                    color = Color.White,
                    text = stringResource(R.string.rituals),
                )
            }
        }
    }
}