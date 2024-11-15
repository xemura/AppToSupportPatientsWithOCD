package com.xenia.apptosupportpatientswithocd.presentation.composable

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.xenia.apptosupportpatientswithocd.domain.entity.Action

@Composable
fun RoundedCornerCheckbox(
    action: Action,
    scriptID: String,
    modifier: Modifier = Modifier,
    size: Float = 24f,
    checkedColor: Color = Color(0xFF0575e6),
    uncheckedColor: Color = Color.White,
    onValueChange: (Boolean) -> Unit,
    onCheckBoxClicked: (String, String, Boolean, String) -> Unit,
) {
    var checkBoxState by remember {
        mutableStateOf(action.checkBoxState)
    }

    val checkboxColor: Color by animateColorAsState(if (checkBoxState) checkedColor else uncheckedColor,
        label = ""
    )
    val density = LocalDensity.current
    val duration = 200

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .heightIn(48.dp)
            .toggleable(
                value = checkBoxState,
                role = Role.Checkbox,
                onValueChange = onValueChange
            )
    ) {
        Box(
            modifier = Modifier
                .size(size.dp)
                .background(color = checkboxColor, shape = RoundedCornerShape(10.dp))
                .border(width = 1.5.dp, color = checkedColor, shape = RoundedCornerShape(10.dp))
                .clickable {
                    checkBoxState = !checkBoxState
                    onCheckBoxClicked(action.id, action.actionText, checkBoxState, scriptID)
                },
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = checkBoxState,
                enter = slideInHorizontally(animationSpec = tween(duration)) {
                    with(density) { (size * -0.5).dp.roundToPx() }
                } + expandHorizontally(
                    expandFrom = Alignment.Start,
                    animationSpec = tween(duration)
                ),
                exit = fadeOut()
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = uncheckedColor
                )
            }
        }
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = action.actionText,
        )
    }
}