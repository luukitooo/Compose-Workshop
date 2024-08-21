package com.luukitoo.compose_examples.advanced_state_and_recomposition.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import java.util.UUID

@Composable
fun UsersListView(
    users: List<User>,
    onUserClicked: (User) -> Unit
) {

    val itemsBackgroundColor by rememberInfiniteTransition().animateColor(
        initialValue = Color.Red,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(tween(durationMillis = 5000)),
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(users, key = { it.id }) { user ->
            Text(
                modifier = Modifier
                    .background(itemsBackgroundColor)
                    .clickable { onUserClicked.invoke(user) }
                    .padding(20.dp),
                text = user.username
            )
        }
    }
}

data class User(
    val id: String,
    val username: String
)

@Preview
@Composable
private fun UsernamesListPreview() = MaterialTheme {
    UsersListView(
        users = List(10) {
            User(
                id = UUID.randomUUID().toString(),
                username = "Username $it"
            )
        },
        onUserClicked = { }
    )
}