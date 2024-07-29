package com.luukitoo.compose_examples.state_holders

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset

@Composable
fun StateHoldersExample() {

    val resizableBoxState = rememberResizableBoxState()

    Box(modifier = Modifier.fillMaxSize()) {
        ResizableBox(state = resizableBoxState)
        Button(
            modifier = Modifier.offset { IntOffset(y = 0, x = resizableBoxState.boxHeight.value.toInt()) },
            onClick = {  }
        ) {
            Text(text = "Hello World!")
        }
    }
}

@Preview
@Composable
private fun StateHoldersExamplePreview() = MaterialTheme {
    StateHoldersExample()
}