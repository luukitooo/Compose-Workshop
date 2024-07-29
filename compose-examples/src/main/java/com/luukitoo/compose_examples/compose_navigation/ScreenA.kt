package com.luukitoo.compose_examples.compose_navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ScreenA() {

    var username by remember { mutableStateOf("") }

    Column {
        TextField(value = username, onValueChange = { username = it })
        Button(onClick = {  }) {
            Text(text = "Continue")
        }
    }
}

object ScreenADestination
