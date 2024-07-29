package com.luukitoo.compose_examples.state_holders

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ResizableBox(
    modifier: Modifier = Modifier,
    state: ResizableBoxState = rememberResizableBoxState()
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .layout { measurable, constraints ->
                val heightPx = state.boxHeight.roundToPx()
                val placeable = measurable.measure(
                    constraints.copy(
                        minHeight = heightPx,
                        maxHeight = heightPx
                    )
                )
                layout(placeable.width, heightPx) {
                    placeable.place(0, 0)
                }
            }
            .background(MaterialTheme.colorScheme.primaryContainer)
            .draggable(state = state.draggableState(), orientation = Orientation.Vertical)
    )
}

@Composable
fun rememberResizableBoxState(minHeight: Dp = 100.dp): ResizableBoxState {
    val configuration = LocalConfiguration.current
    return rememberSaveable { ResizableBoxState(configuration, minHeight) }
}

@Stable
class ResizableBoxState(
    private val configuration: Configuration,
    private val minHeight: Dp
) {

    var boxHeight by mutableStateOf(minHeight)
        private set

    @Composable
    fun draggableState() = rememberDraggableState { delta ->
        val currentSize = boxHeight + delta.dp / 2.65f
        if (currentSize > minHeight && currentSize < configuration.screenHeightDp.dp) {
            boxHeight = currentSize
        }
    }
}
