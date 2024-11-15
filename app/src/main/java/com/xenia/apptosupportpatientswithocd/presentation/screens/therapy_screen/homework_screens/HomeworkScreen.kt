package com.xenia.apptosupportpatientswithocd.presentation.screens.therapy_screen.homework_screens

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xenia.apptosupportpatientswithocd.R
import com.xenia.apptosupportpatientswithocd.domain.entity.HomeworkModel
import com.xenia.apptosupportpatientswithocd.presentation.composable.topbar.TopBarWithArrowBack
import com.xenia.apptosupportpatientswithocd.presentation.getApplicationComponent
import com.xenia.apptosupportpatientswithocd.presentation.states.HomeworkScreenState
import com.xenia.apptosupportpatientswithocd.presentation.viewmodels.HomeworkViewModel

@Composable
fun HomeworkScreenContentState(
    onBackPressed: () -> Unit,
    onAddPressed: () -> Unit,
    onStatisticPressed: (HomeworkModel) -> Unit,
    onEditPressed: (HomeworkModel) -> Unit,
    onPracticePressed: (HomeworkModel) -> Unit,
    onDeleteSwiped: (String) -> Unit,
) {
    val component = getApplicationComponent()
    val homeworkViewModel: HomeworkViewModel = viewModel(factory = component.getViewModelFactory())

    val screenState = homeworkViewModel.screenState.collectAsState(HomeworkScreenState.Initial)

    when (val currentState = screenState.value) {
        is HomeworkScreenState.HomeworkMain -> {
            HomeworkScreen(
                currentState.homeworksList,
                { onBackPressed() },
                { onAddPressed() },
                { homework ->
                    onStatisticPressed(homework)
                },
                { onEditPressed(it) },
                { onPracticePressed(it) },
                { onDeleteSwiped(it) }
            )
        }

        HomeworkScreenState.Initial -> {

        }

        HomeworkScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.Black)
            }
        }

        else -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeworkScreen(
    homeworksList: List<HomeworkModel>?,
    onBackPressed: () -> Unit,
    onAddPressed: () -> Unit,
    onStatisticPressed: (HomeworkModel) -> Unit,
    onEditPressed: (HomeworkModel) -> Unit,
    onPracticePressed: (HomeworkModel) -> Unit,
    onDeleteSwiped: (String) -> Unit,
) {

    var show by remember { mutableStateOf(true) }
    var currentItemID by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBarWithArrowBack(topBarName = stringResource(R.string.homeworks)) {
                onBackPressed()
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = contentPadding.calculateTopPadding() + 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(R.string.homeworks_tasks))
                IconButton(onClick = { onAddPressed() }) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }

            if (homeworksList != null) {
                LazyColumn {

                    items(homeworksList) { homework ->

                        val dismissState = rememberSwipeToDismissBoxState(
                            confirmValueChange = {
                                if (it == SwipeToDismissBoxValue.EndToStart) {
                                    show = false
                                    currentItemID = homework.id
                                    true
                                } else false
                            },
                            positionalThreshold = { 150.dp.value }
                        )

                        SwipeToDismissBox(
                            modifier = Modifier
                                .animateContentSize()
                                .fillMaxWidth()
                                .padding(horizontal = 30.dp, vertical = 5.dp),
                            state = dismissState,
                            enableDismissFromStartToEnd = false,
                            backgroundContent = {
                                val color = Color(0xFFFF1744)
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(color),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Icon(
                                        Icons.Default.Delete,
                                        modifier = Modifier
                                            .minimumInteractiveComponentSize(),
                                        contentDescription = stringResource(R.string.delete),
                                        tint = Color.White
                                    )
                                }
                            }
                        ) {
                            HomeworkCard(
                                homework = homework,
                                onStatisticPressed = { homework ->
                                    onStatisticPressed(homework)
                                },
                                onEditPressed = { onEditPressed(it) },
                                onPracticePressed = { onPracticePressed(it) }
                            )
                        }

                        if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
                            LaunchedEffect(dismissState) {
                                onDeleteSwiped(currentItemID)
                                dismissState.snapTo(SwipeToDismissBoxValue.Settled)
                                Toast.makeText(context,
                                    context.getString(R.string.homework_delete), Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            } else {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.empty_list_homeworks),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun HomeworkCard(
    homework: HomeworkModel,
    onStatisticPressed: (HomeworkModel) -> Unit,
    onEditPressed: (HomeworkModel) -> Unit,
    onPracticePressed: (HomeworkModel) -> Unit,
) {
    Card(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .clickable {
                onEditPressed(homework)
            },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)

        ) {
            Text(
                modifier = Modifier.padding(bottom = 5.dp, start = 5.dp),
                text = homework.obsessionInfo
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = {
                        onPracticePressed(homework)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = stringResource(R.string.practice),
                        fontSize = 15.sp
                    )
                }
                Spacer(modifier = Modifier.padding(3.dp))
                Button(
                    onClick = {
                        onStatisticPressed(homework)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = stringResource(R.string.statistics),
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}
