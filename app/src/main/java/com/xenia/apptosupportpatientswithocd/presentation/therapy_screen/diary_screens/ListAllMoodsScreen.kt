package com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.diary_screens

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xenia.apptosupportpatientswithocd.domain.entity.MoodModel
import com.xenia.apptosupportpatientswithocd.presentation.getApplicationComponent

@Composable
fun MainAllMoodsListScreen(
    onBackPressed: () -> Unit,
    onEditPressed: (MoodModel) -> Unit,
    onDeleteMood: (String) -> Unit,
) {
    val component = getApplicationComponent()
    val moodViewModel: MoodViewModel = viewModel(factory = component.getViewModelFactory())

    val screenState = moodViewModel.screenState.collectAsState(MoodScreenState.Initial)

    when (val currentState = screenState.value) {
        is MoodScreenState.MoodsMain -> {
            Log.d("TAG", "Mood Loading")
            currentState.moodList?.let { moodList ->
                ListAllMoodsScreen(
                    { onBackPressed() },
                    { onEditPressed(it) },
                    moodList,
                    { onDeleteMood(it) },
                )
            }
        }
        MoodScreenState.Initial -> {
            Log.d("TAG", "Mood Initial")
        }
        MoodScreenState.Loading -> {
            Log.d("TAG", "Mood Loading")
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center)
            {
                CircularProgressIndicator(color = Color.Black)
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListAllMoodsScreen(
    onBackPressed: () -> Unit,
    onEditPressed: (MoodModel) -> Unit,
    moodsList: List<MoodModel>,
    onDeleteMood: (String) -> Unit,
) {
    var show by remember { mutableStateOf(true) }
    var currentItem by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .shadow(
                        elevation = 5.dp,
                        spotColor = Color.DarkGray
                    ),
                title = {
                    Text(text = "Список записей")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    titleContentColor = Color.White,
                    containerColor = Color(0xFF101018)
                ),
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = contentPadding.calculateTopPadding(),
                    bottom = contentPadding.calculateTopPadding() + 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(moodsList) { mood ->
                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = {
                        if (it == SwipeToDismissBoxValue.EndToStart) {
                            show = false
                            currentItem = mood.id
                            true
                        } else false
                    },
                    positionalThreshold = { 150.dp.value }
                )

                Log.d("TAG", show.toString())
                Log.d("TAG", dismissState.currentValue.toString())

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
                                contentDescription = "delete",
                                tint = Color.White
                            )
                        }
                    }
                ) {
                    Card(
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                            .clickable {
                                onEditPressed(mood)
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
                            text = "${mood.time}  ${mood.assessment}/10"
                        )
                    }
                }

                LaunchedEffect(show) {
                    if (!show) {
                        onDeleteMood(currentItem)
                        dismissState.snapTo(SwipeToDismissBoxValue.Settled)
                        Toast.makeText(context, "Item removed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}