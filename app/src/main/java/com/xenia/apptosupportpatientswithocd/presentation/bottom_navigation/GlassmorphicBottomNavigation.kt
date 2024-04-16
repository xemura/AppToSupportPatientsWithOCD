package com.xenia.apptosupportpatientswithocd.presentation.bottom_navigation

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.xenia.apptosupportpatientswithocd.navigation.NavigationItem
import com.xenia.apptosupportpatientswithocd.navigation.NavigationState
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild

@Composable
fun GlassmorphicBottomNavigation(
    navigationState: NavigationState,
    hazeState: HazeState,
    tabs: List<NavigationItem>
) {
    var selectedTabIndex by remember { mutableIntStateOf(2) }
    Box(
        modifier = Modifier
            .padding(vertical = 24.dp, horizontal = 30.dp)
            .fillMaxWidth()
            .height(64.dp)
            .hazeChild(state = hazeState, shape = CircleShape)
            .border(
                width = 2.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = .3f),
                        Color.Black.copy(alpha = .3f),
                    ),
                ),
                shape = CircleShape
            )
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            BottomBarTabs(
                tabs,
                selectedTab = selectedTabIndex,
                onTabSelected = {
                    Log.d("TAG", "BottomBarTabs: ${it.route}")
                    selectedTabIndex = tabs.indexOf(it)
                    navigationState.navHostController.popBackStack() //
                    navigationState.navigateTo(it.route)
                }
            )
        }

        val animatedSelectedTabIndex by animateFloatAsState(
            targetValue = selectedTabIndex.toFloat(), label = "animatedSelectedTabIndex",
            animationSpec = spring(
                stiffness = Spring.StiffnessLow,
                dampingRatio = Spring.DampingRatioLowBouncy,
            )
        )

        val animatedColor by animateColorAsState(
            targetValue = tabs[selectedTabIndex].color,
            label = "animatedColor",
            animationSpec = spring(
                stiffness = Spring.StiffnessLow,
            )
        )

        Log.d("BottomBar", animatedSelectedTabIndex.toString())

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .blur(50.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
        ) {
            val tabWidth = size.width / tabs.size
            drawCircle(
                color = animatedColor.copy(alpha = .9f),
                radius = size.height / 2,
                center = Offset(
                    (tabWidth * animatedSelectedTabIndex) + tabWidth / 2,
                    size.height / 2
                )
            )
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        ) {
            val path = Path().apply {
                addRoundRect(RoundRect(size.toRect(), CornerRadius(size.height)))
            }
            val length = PathMeasure().apply { setPath(path, false) }.length

            val tabWidth = size.width / tabs.size
            drawPath(
                path,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        animatedColor.copy(alpha = 0f),
                        animatedColor.copy(alpha = 1f),
                        animatedColor.copy(alpha = 1f),
                        animatedColor.copy(alpha = 0f),
                    ),
                    startX = tabWidth * animatedSelectedTabIndex,
                    endX = tabWidth * (animatedSelectedTabIndex + 1),
                ),
                style = Stroke(
                    width = 6f,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(length / 2, length)
                    )
                )
            )
        }
    }
}