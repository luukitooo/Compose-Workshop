package com.luukitoo.compose_examples.advanced_state_and_recomposition.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.rounded.PersonPin
import androidx.compose.material.icons.rounded.PersonPinCircle
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoView(
    username: String,
    email: String,
    initialColor: Color,
    paddingStart: Dp = 15.dp,
    paddingTop: Dp = 15.dp,
    paddingEnd: Dp = 15.dp,
    paddingBottom: Dp = 15.dp
) {

    val colorHelper = ColorHelper()
    var iconColor by remember { mutableStateOf(initialColor) }
    var isExpanded by remember { mutableStateOf(false) }
    val arrowRotation by animateFloatAsState(if (!isExpanded) 0f else 180f)


    Card(
        onClick = {
            iconColor = colorHelper.randomColor()
            isExpanded = !isExpanded
        }
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = paddingStart,
                    top = paddingTop,
                    end = paddingEnd,
                    bottom = paddingBottom
                )
                .background(Color.Yellow),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Icon(
                modifier = Modifier.size(50.dp),
                imageVector = Icons.Rounded.PersonPin,
                contentDescription = null,
                tint = iconColor
            )
            Column {
                Text(text = username, fontWeight = FontWeight.SemiBold)
                Text(text = email)
            }
            Icon(
                modifier = Modifier.rotate(arrowRotation),
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }
    }
}

class ColorHelper {
    fun randomColor() = Color.hsl(
        Random.nextFloat(),
        Random.nextFloat(),
        Random.nextFloat(),
    )
}

@Preview
@Composable
private fun UserInfoViewPreview() = MaterialTheme {
    UserInfoView(
        username = "Username",
        email = "test@gmail.com",
        initialColor = Color.Red
    )
}