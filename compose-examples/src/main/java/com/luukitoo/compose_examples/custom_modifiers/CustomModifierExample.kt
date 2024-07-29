package com.luukitoo.compose_examples.custom_modifiers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomModifierExample() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier.cross(
                color = Color.Red,
                strokeWidth = 10.dp
            ),
            onClick = { }
        ) {
            Text(text = "Click Me!")
        }
    }
}

@Preview
@Composable
private fun CustomModifierExamplePreview() = MaterialTheme {
    CustomModifierExample()
}