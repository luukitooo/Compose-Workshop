@file:OptIn(ExperimentalMaterial3Api::class)

package com.luukitoo.compose_examples.other_best_practices

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NonRestartableComposableExample() {

    var isChipSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CustomChip(
            isSelected = isChipSelected,
            onClick = { isChipSelected = !isChipSelected }
        ) {
            Text(text = "Category", fontSize = 24.sp)
        }
    }
}

@NonRestartableComposable
@Composable
fun CustomChip(
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: CustomChipColors = CustomChipDefaults.defaultCustomChipColors(),
    shape: Shape = CircleShape,
    paddingValues: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
    content: @Composable RowScope.() -> Unit
) {
    CompositionLocalProvider(
        value = LocalContentColor provides if (isSelected) {
            colors.selectedContentColor
        } else {
            colors.unselectedContentColor
        }
    ) {
        Row(
            modifier = modifier
                .clip(shape)
                .background(
                    color = if (isSelected) {
                        colors.selectedBackgroundColor
                    } else {
                        colors.unselectedBackgroundColor
                    }
                )
                .clickable { onClick.invoke() }
                .padding(paddingValues),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            content = content,
        )
    }
}

object CustomChipDefaults {

    @Composable
    fun defaultCustomChipColors(
        selectedBackgroundColor: Color = MaterialTheme.colorScheme.primary,
        unselectedBackgroundColor: Color = MaterialTheme.colorScheme.inversePrimary,
        selectedContentColor: Color = MaterialTheme.colorScheme.onPrimary,
        unselectedContentColor: Color = MaterialTheme.colorScheme.primary
    ) = CustomChipColors(
        selectedBackgroundColor = selectedBackgroundColor,
        unselectedBackgroundColor = unselectedBackgroundColor,
        selectedContentColor = selectedContentColor,
        unselectedContentColor = unselectedContentColor
    )
}

data class CustomChipColors(
    val selectedBackgroundColor: Color,
    val unselectedBackgroundColor: Color,
    val selectedContentColor: Color,
    val unselectedContentColor: Color
)

@Preview
@Composable
private fun NonRestartableComposableExamplePreview() {
    MaterialTheme {
        NonRestartableComposableExample()
    }
}