package com.luukitoo.composeworkshop.compose_ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

@Composable
fun <T> SharedFlowCollector(
    sharedFlow: SharedFlow<T>,
    onEmit: (T) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        withContext(Dispatchers.Main.immediate) {
            sharedFlow.collect(onEmit)
        }
    }
}
