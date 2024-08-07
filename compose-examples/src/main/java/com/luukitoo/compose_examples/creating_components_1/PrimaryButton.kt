package com.luukitoo.compose_examples.creating_components_1

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    buttonText : String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = false,
    clickable: () -> Unit,
    shape: Shape = RoundedCornerShape(10.dp)
) {
    val isEnableState by remember { mutableStateOf(isEnabled) }
    val colorPair : Pair<Color, Color> = remember(key1 = isEnabled) {
        if (isEnableState) {
            Pair(Color.DarkGray, Color.White)
        } else {
            Pair(Color.Gray, Color.Black)
        }
    }
    Row(
        modifier
            .clip(shape)
            .fillMaxWidth()
            .height(40.dp)
            .background(colorPair.first)
            .clickable {
                clickable()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null, tint = colorPair.second)
        Text(text = buttonText, color = colorPair.second, fontWeight = FontWeight(500))
    }
}