package com.luukitoo.composeworkshop.compose_ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun ScrollUpButton(
    text: @Composable RowScope.() -> Unit,
    scrollState: LazyListState,
    modifier: Modifier = Modifier,
) {

    val scope = rememberCoroutineScope()

    val isVisible by remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex > 5
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Button(
            modifier = modifier,
            onClick = {
                scope.launch {
                    scrollState.animateScrollToItem(0)
                }
            },
            content = text
        )
    }
}