package com.xenia.apptosupportpatientswithocd.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = ValueRange,
    gap: Int = Gap,
    showIndicator: Boolean = false,
    showLabel: Boolean = false,
    enabled: Boolean = true,
    thumb: @Composable (thumbValue: Int) -> Unit = {
        CustomSliderDefaults.Thumb(it.toString())
    },
    track: @Composable (sliderState: SliderState) -> Unit = { sliderState ->
        CustomSliderDefaults.Track(sliderState = sliderState)
    },
    indicator: @Composable (indicatorValue: Int) -> Unit = { indicatorValue ->
        CustomSliderDefaults.Indicator(indicatorValue = indicatorValue.toString())
    },
    label: @Composable (labelValue: Int) -> Unit = { labelValue ->
        CustomSliderDefaults.Label(labelValue = labelValue.toString())
    }
) {
    val itemCount = (valueRange.endInclusive - valueRange.start).roundToInt()
    val steps = if (gap == 1) 0 else (itemCount / gap - 1)

    Box(modifier = modifier) {
        Layout(
            measurePolicy = customSliderMeasurePolicy(
                itemCount = itemCount,
                gap = gap,
                value = value,
                startValue = valueRange.start
            ),
            content = {
                if (showLabel)
                    Label(
                        modifier = Modifier.layoutId(CustomSliderComponents.LABEL),
                        value = value,
                        label = label
                    )

                Box(modifier = Modifier.layoutId(CustomSliderComponents.THUMB)) {
                    thumb(value.roundToInt())
                }

                Slider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .layoutId(CustomSliderComponents.SLIDER),
                    value = value,
                    valueRange = valueRange,
                    steps = steps,
                    onValueChange = { onValueChange(it) },
                    thumb = {
                        thumb(value.roundToInt())
                    },
                    track = { track(it) },
                    enabled = enabled
                )

                if (showIndicator)
                    Indicator(
                        modifier = Modifier.layoutId(CustomSliderComponents.INDICATOR),
                        valueRange = valueRange,
                        gap = gap,
                        indicator = indicator
                    )
            })
    }
}

@Composable
private fun Label(
    modifier: Modifier = Modifier,
    value: Float,
    label: @Composable (labelValue: Int) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        label(value.roundToInt())
    }
}

@Composable
private fun Indicator(
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float>,
    gap: Int,
    indicator: @Composable (indicatorValue: Int) -> Unit
) {
    for (i in valueRange.start.roundToInt()..valueRange.endInclusive.roundToInt() step gap) {
        Box(modifier = modifier) {
            indicator(i)
        }
    }
}

private fun customSliderMeasurePolicy(
    itemCount: Int,
    gap: Int,
    value: Float,
    startValue: Float
) = MeasurePolicy { measurables, constraints ->
    val thumbPlaceable = measurables.first {
        it.layoutId == CustomSliderComponents.THUMB
    }.measure(constraints)
    val thumbRadius = (thumbPlaceable.width / 2).toFloat()

    val indicatorPlaceables = measurables.filter {
        it.layoutId == CustomSliderComponents.INDICATOR
    }.map { measurable ->
        measurable.measure(constraints)
    }
    val indicatorHeight = indicatorPlaceables.maxByOrNull { it.height }?.height ?: 0

    val sliderPlaceable = measurables.first {
        it.layoutId == CustomSliderComponents.SLIDER
    }.measure(constraints)
    val sliderHeight = sliderPlaceable.height

    val labelPlaceable = measurables.find {
        it.layoutId == CustomSliderComponents.LABEL
    }?.measure(constraints)
    val labelHeight = labelPlaceable?.height ?: 0

    // Calculate the total width and height of the custom slider layout
    val width = sliderPlaceable.width
    val height = labelHeight + sliderHeight + indicatorHeight

    // Calculate the available width for the track (excluding thumb radius on both sides).
    val trackWidth = width - (2 * thumbRadius)

    // Calculate the width of each section in the track.
    val sectionWidth = trackWidth / itemCount
    // Calculate the horizontal spacing between indicators.
    val indicatorSpacing = sectionWidth * gap

    // To calculate offset of the label, first we will calculate the progress of the slider
    // by subtracting startValue from the current value.
    // After that we will multiply this progress by the sectionWidth.
    // Add thumb radius to this resulting value.
    val labelOffset = (sectionWidth * (value - startValue)) + thumbRadius

    layout(width = width, height = height) {
        var indicatorOffsetX = thumbRadius
        // Place label at top.
        // We have to subtract the half width of the label from the labelOffset,
        // to place our label at the center.
        labelPlaceable?.placeRelative(
            x = (labelOffset - (labelPlaceable.width / 2)).roundToInt(),
            y = 0
        )

        // Place slider placeable below the label.
        sliderPlaceable.placeRelative(x = 0, y = labelHeight)

        // Place indicators below the slider.
        indicatorPlaceables.forEach { placeable ->
            // We have to subtract the half width of the each indicator from the indicatorOffset,
            // to place our indicators at the center.
            placeable.placeRelative(
                x = (indicatorOffsetX - (placeable.width / 2)).roundToInt(),
                y = labelHeight + sliderHeight
            )
            indicatorOffsetX += indicatorSpacing
        }
    }
}

object CustomSliderDefaults {
    @Composable
    fun Thumb(
        thumbValue: String,
        modifier: Modifier = Modifier,
        color: Color = PrimaryColor,
        size: Dp = ThumbSize,
        shape: Shape = CircleShape,
        content: @Composable () -> Unit = {
            Text(
                text = thumbValue,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    ) {
        Box(
            modifier = modifier
                .defaultMinSize(minWidth = size, minHeight = size)
                .clip(shape)
                .background(color)
                .padding(2.dp),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Track(
        sliderState: SliderState,
        modifier: Modifier = Modifier,
        trackColor: Color = TrackColor,
        progressColor: Color = PrimaryColor,
        height: Dp = TrackHeight,
        shape: Shape = CircleShape
    ) {
        Box(
            modifier = modifier
                .track(height = height, shape = shape)
                .background(trackColor)
        ) {
            Box(
                modifier = Modifier
                    .progress(
                        sliderPositions = sliderState,
                        height = height,
                        shape = shape
                    )
                    .background(progressColor)
            )
        }
    }

    @Composable
    fun Indicator(
        indicatorValue: String,
        modifier: Modifier = Modifier,
        style: TextStyle = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Normal)
    ) {
        Box(modifier = modifier) {
            Text(
                text = indicatorValue,
                style = style,
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    fun Label(
        labelValue: String,
        modifier: Modifier = Modifier,
        style: TextStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal)
    ) {
        Box(modifier = modifier) {
            Text(
                text = labelValue,
                style = style,
                textAlign = TextAlign.Center
            )
        }
    }
}

fun Modifier.track(
    height: Dp = TrackHeight,
    shape: Shape = CircleShape
) = fillMaxWidth()
    .heightIn(min = height)
    .clip(shape)

@OptIn(ExperimentalMaterial3Api::class)
fun Modifier.progress(
    sliderPositions: SliderState,
    height: Dp = TrackHeight,
    shape: Shape = CircleShape
) =
    fillMaxWidth(fraction = sliderPositions.value / 10f)
        .heightIn(min = height)
        .clip(shape)


private enum class CustomSliderComponents {
    SLIDER, LABEL, INDICATOR, THUMB
}

val PrimaryColor = Color(0xFF6650a4)
val TrackColor = Color(0xFFE7E0EC)

private const val Gap = 1
private val ValueRange = 0f..10f
private val TrackHeight = 8.dp
private val ThumbSize = 30.dp