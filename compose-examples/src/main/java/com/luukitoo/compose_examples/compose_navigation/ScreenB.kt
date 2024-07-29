package com.luukitoo.compose_examples.compose_navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun ScreenB(username: String) {
    Text(
        text = username,
        fontSize = 36.sp
    )
}

data class ScreenBDestination(
    val username: String
)
