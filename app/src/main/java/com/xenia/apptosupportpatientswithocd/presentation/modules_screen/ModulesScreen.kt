package com.xenia.apptosupportpatientswithocd.presentation.modules_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xenia.apptosupportpatientswithocd.data.modules_data.modulesList
import com.xenia.apptosupportpatientswithocd.domain.entity.ModuleContentModel
import com.xenia.apptosupportpatientswithocd.domain.entity.ModuleModel
import com.xenia.apptosupportpatientswithocd.presentation.composable.TopBarWithoutArrowBack
import com.xenia.apptosupportpatientswithocd.presentation.getApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

@Composable
fun ModulesScreen(
    onModuleImageClickListener: (List<ModuleContentModel>) -> Unit
) {

    val component = getApplicationComponent()
    val viewModel: ModulesViewModel = viewModel(factory = component.getViewModelFactory())

    val scope = CoroutineScope(Dispatchers.Default)

    var data by remember {
        mutableStateOf<List<ModuleModel>?>(null)
    }

    LaunchedEffect(
        key1 = null
    ) {
        data = scope.async {
            viewModel.getModulesList()
        }.await()
    }

    Scaffold(
        topBar = {
            TopBarWithoutArrowBack(topBarName = "Модули")
        }
    ) { contentPadding ->


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    bottom = contentPadding.calculateTopPadding() + 30.dp
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(modulesList) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 30.dp)
                        .fillMaxWidth(),
                    color = Color.Black,
                    fontSize = 20.sp,
                    text = it.name,
                    textAlign = TextAlign.Start
                )

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .height(200.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .clickable {
                            onModuleImageClickListener(it.contentList)
                        },
                    painter = painterResource(id = it.image),
                    contentDescription = "Картинка на главном экране",
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}