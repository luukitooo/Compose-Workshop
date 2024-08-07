package com.luukitoo.compose_examples.advanced_state_and_recomposition

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import java.util.UUID

@Composable
fun Examples() {

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val userViewBackground by rememberInfiniteTransition(label = "").animateColor(
        initialValue = Color.Cyan,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(tween(durationMillis = 1000)),
        label = ""
    )

    Column {
        UserView(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .drawBehind { drawRect(userViewBackground) },
            userValues = remember {
                UserValues(
                    username = "Cool Developer",
                    email = "test@gmail.com",
                    interests = listOf("Android", "App Development").toImmutableList()
                )
            }
        )
        TextField(
            value = username,
            label = { Text("Username") },
            onValueChange = {
                username = it
            }
        )
        TextField(
            value = email,
            label = { Text("Email") },
            onValueChange = {
                email = it
            }
        )
    }
}

@Composable
fun UserView(
    userValues: UserValues,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null
        )
        Column {
            Text(text = userValues.username)
            Text(text = userValues.email)
            Text(text = userValues.interests.toString())
        }
    }
}

data class UserValues(
    val username: String,
    val email: String,
    val interests: ImmutableList<String>
)

@Preview
@Composable
private fun ExamplesPreview() = MaterialTheme {
    Examples()
}