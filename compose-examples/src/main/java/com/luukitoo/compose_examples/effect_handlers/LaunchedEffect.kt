package com.luukitoo.compose_examples.effect_handlers

import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LaunchedEffectExample() {

    val context = LocalContext.current
    var count by remember { mutableStateOf(0) }

    LaunchedEffect(count) {
        Toast.makeText(context, count.toString(), Toast.LENGTH_SHORT).show()
    }

    Button(onClick = { count++ }) {
        Text(text = "Increase!")
    }
}

@Preview
@Composable
private fun LaunchedEffectExamplePreview() {
    LaunchedEffectExample()
}