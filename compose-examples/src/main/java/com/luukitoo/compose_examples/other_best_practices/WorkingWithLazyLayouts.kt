package com.luukitoo.compose_examples.other_best_practices

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import java.util.UUID

@Composable
fun LazyLayoutsExample() {

    val videoGames by remember { mutableStateOf(List(10) { VideoGame() }) }

    val filteredGames = remember(videoGames) {
        videoGames.filter { it.id.contains("15") }
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(filteredGames, key = { it.id }) { game ->
            VideoGameItem(videoGame = game)
        }
    }
}

@Composable
fun VideoGameItem(videoGame: VideoGame) {
    Text(text = videoGame.id)
}

@Preview
@Composable
private fun LazyLayoutsExamplePreview() = MaterialTheme {
    LazyLayoutsExample()
}

data class VideoGame(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val releaseDate: Long = 0L,
    val studio: String = ""
)