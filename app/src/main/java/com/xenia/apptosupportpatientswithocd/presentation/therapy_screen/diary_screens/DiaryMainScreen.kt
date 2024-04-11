package com.xenia.apptosupportpatientswithocd.presentation.therapy_screen.diary_screens

import android.util.Log
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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xenia.apptosupportpatientswithocd.R
import com.xenia.apptosupportpatientswithocd.domain.entity.MoodModel
import com.xenia.apptosupportpatientswithocd.presentation.composable.BarGraph
import com.xenia.apptosupportpatientswithocd.presentation.composable.BarType
import com.xenia.apptosupportpatientswithocd.presentation.getApplicationComponent
import kotlinx.coroutines.delay


@Composable
fun DiaryMoodMainContent(
    onBackPressed: () -> Unit,
    onEditPressed: (MoodModel) -> Unit,
    onAddPressed: () -> Unit,
    onDeleteMood: (String) -> Unit,
) {
    val component = getApplicationComponent()
    val moodViewModel: MoodViewModel = viewModel(factory = component.getViewModelFactory())

    val screenState = moodViewModel.screenState.collectAsState(MoodScreenState.Initial)

    when (val currentState = screenState.value) {
        is MoodScreenState.MoodsMain -> {
            Log.d("TAG", "Mood Loading")
            DiaryMainScreen(
                currentState.moodList,
                { onBackPressed() },
                { onEditPressed(it) },
                { onAddPressed() },
                { onDeleteMood(it) }
            )
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
fun DiaryMainScreen(
    moodsList: List<MoodModel>?,
    onBackPressed: () -> Unit,
    onEditPressed: (MoodModel) -> Unit,
    onAddPressed: () -> Unit,
    onDeleteMood: (String) -> Unit,
) {

    var show by remember { mutableStateOf(true) }
    var currentItem by remember {mutableStateOf("") }
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
                    Text(text = "Дневник настроения")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    titleContentColor = Color.White,
                    containerColor = Color(0xFF101018)
                ),
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
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
                .padding(top = contentPadding.calculateTopPadding()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column {
                    val dataList = mutableListOf(3,6,10,5,7)
                    val floatValue = mutableListOf<Float>()
                    val datesList = mutableListOf(
                        "01.04", "02.04",
                        "03.04", "04.04",
                        "05.04"
                    )

                    dataList.forEachIndexed { index, value ->
                        floatValue.add(index = index, element = value.toFloat()/dataList.max().toFloat())
                    }

                    BarGraph(
                        graphBarData = floatValue,
                        xAxisScaleData = datesList,
                        barData_ = dataList,
                        height = 280.dp,
                        roundType = BarType.TOP_CURVED,
                        barWidth = 20.dp,
                        barBrush = Brush.verticalGradient(
                            listOf(
                                Color(0xFF0575e6),
                                Color(0xFFb5e2fa)
                            )
                        ),
                        barArrangement = Arrangement.SpaceEvenly
                    )

                    Text(
                        modifier = Modifier.padding(horizontal = 30.dp, vertical = 5.dp),
                        text = "Ваше настроение по дням:"
                    )
                }

            }

            if (moodsList != null) {
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
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Посмотреть все записи о настроении",
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            item {
                Button(
                    onClick = { onAddPressed() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0575e6)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 5.dp),

                    ) {
                    Text(
                        color = Color.White,
                        text = "Добавить",
                    )
                }
            }
        }
    }

}