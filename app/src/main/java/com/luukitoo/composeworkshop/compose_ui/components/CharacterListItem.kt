package com.luukitoo.composeworkshop.compose_ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.luukitoo.composeworkshop.rick_and_morty_api.data.model.CharacterStatus

@Composable
fun CharacterListItem(
    values: CharacterListItemValues,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(onClick = onClick) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = values.status.name,
                    color = values.status.color,
                    fontSize = 20.sp
                )
                Text(text = values.name)
            }
            AsyncImage(
                modifier = Modifier.clip(RoundedCornerShape(12.dp)),
                model = values.image,
                contentDescription = null
            )
        }
    }
}

data class CharacterListItemValues(
    val name: String,
    val status: CharacterStatus,
    val image: String,
)