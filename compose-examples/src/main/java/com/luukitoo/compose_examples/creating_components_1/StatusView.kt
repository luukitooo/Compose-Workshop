package com.luukitoo.compose_examples.creating_components_1

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatusIcon(status: Status) {
    Icon(
        modifier = Modifier.size(96.dp),
        imageVector = when (status) {
            Status.SUCCESS -> {
                Icons.Default.Check
            }

            Status.ERROR -> {
                Icons.Default.ErrorOutline
            }

            Status.PROGRESS -> {
                Icons.Default.Android
            }
        },
        tint = when (status) {
            Status.SUCCESS -> {
                Color.Green
            }

            Status.ERROR -> {
                Color.Red
            }

            Status.PROGRESS -> {
                Color.Yellow
            }
        },
        contentDescription = null
    )
}

enum class Status {
    SUCCESS, ERROR, PROGRESS
}

@Composable
fun StatusView(
    title: String,
    body: String,
    modifier: Modifier = Modifier,
    statusImage: @Composable () -> Unit = {
        StatusIcon(status = Status.PROGRESS)
    },
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        statusImage.invoke()
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = title,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.W500
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = body,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.W400
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StatusViewPreview() {
    StatusView(title = "Title text", body = "Body text explaining the result")
}
