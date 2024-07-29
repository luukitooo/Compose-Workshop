package com.luukitoo.compose_examples.effect_handlers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun CountDownTimer(
    from: Long,
    modifier: Modifier = Modifier,
    onTimeChanged: (Long) -> Unit = { },
    textStyle: TextStyle = LocalTextStyle.current.copy(
        fontSize = 50.sp,
        color = MaterialTheme.colorScheme.onSurface
    )
) {

    val coroutineScope = rememberCoroutineScope()

    var time by remember(from) { mutableLongStateOf(from) }

    Text(
        modifier = modifier,
        text = time.toString(),
        style = textStyle
    )

    SideEffect {
        if (time > 0) {
            coroutineScope.launch {
                delay(1000L)
                time--
                onTimeChanged.invoke(time)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CountDownTimerPreview() = MaterialTheme {

    var from by remember { mutableLongStateOf(50L) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CountDownTimer(from = from)
        Button(onClick = { from = Random.nextLong(100) }) {
            Text(text = "Reset with random target")
        }
    }
}