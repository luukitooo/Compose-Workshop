package com.luukitoo.compose_examples.effect_handlers

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchField(
    onQuery: (String) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    searchIcon: @Composable () -> Unit = {
        Icon(Icons.Default.Search, null)
    },
    clearIcon: @Composable () -> Unit = {
        Icon(Icons.Default.Close, null)
    },
    queryDelay: Long = 500L,
) {

    var queryJob = remember<Job?> { null }
    var value by remember { mutableStateOf("") }

    LaunchedEffect(key1 = value) {
        queryJob?.cancel()
        queryJob = launch {
            delay(queryDelay)
            onQuery.invoke(value)
        }
    }

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { value = it },
        label = { Text(text = "Search") },
        placeholder = { Text(text = "Search") },
        singleLine = true,
        shape = shape,
        leadingIcon = searchIcon,
        trailingIcon = {
            AnimatedVisibility(
                visible = value.isNotBlank(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                IconButton(onClick = { value = "" }) {
                    clearIcon.invoke()
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchFieldPreview() = MaterialTheme {

    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text)
        SearchField(
            modifier = Modifier.fillMaxWidth(0.75f),
            onQuery = { text = it }
        )
    }
}