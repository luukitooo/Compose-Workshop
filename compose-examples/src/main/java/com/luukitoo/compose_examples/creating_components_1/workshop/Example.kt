package com.luukitoo.compose_examples.creating_components_1.workshop

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectedUser(
    username: String,
    color: Color,
    modifier: Modifier = Modifier,
    background: Color = Color.White,
    shape: Shape = RoundedCornerShape(10.dp),
    dropDownIcon: @Composable () -> Unit = {
        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
    },
    textStyle: TextStyle = LocalTextStyle.current
) {
    Row(
        modifier = Modifier
            .clip(shape)
            .background(background)
            .border(1.dp, Color.Gray, shape)
            .padding(horizontal = 20.dp, vertical = 14.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserIcon(color = color, initial = username.first())
        Spacer(modifier = Modifier.width(14.dp))
        Text(text = username, style = textStyle)
        Spacer(modifier = Modifier.weight(1f))
        dropDownIcon.invoke()
    }
}

@Composable
fun UserIcon(
    color: Color,
    initial: Char
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                color = color.copy(alpha = 0.5f),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initial.toString(),
            color = color,
            fontSize = 24.sp
        )
    }
}

@Preview
@Composable
private fun SomeComponentPreview() {
    SelectedUser(
        username = "Android Studio 235",
        color = Color.Magenta,
    )
}