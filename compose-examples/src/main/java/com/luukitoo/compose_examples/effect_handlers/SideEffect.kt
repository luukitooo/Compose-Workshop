package com.luukitoo.compose_examples.effect_handlers

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SideEffectExample() {

    val scope = rememberCoroutineScope()
    var time by remember { mutableStateOf(0) }

    Text(text = time.toString())

    SideEffect {
        scope.launch {
            delay(1000L)
            time++
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SideEffectExamplePreview() {
    SideEffectExample()
}