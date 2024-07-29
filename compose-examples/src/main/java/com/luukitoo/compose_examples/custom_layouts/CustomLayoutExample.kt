package com.luukitoo.compose_examples.custom_layouts

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun CustomLayoutExample() {
    MyCustomLayout()
}

@Composable
fun MyCustomLayout(
    itemSpacing: Dp = 16.dp
) {
    Layout(
        content = {
            Text(text = "Hello")
            Text(text = "World")
            Text(text = "World")
            Text(text = "World")
            Button(onClick = { /*TODO*/ }) {
                Text(text = "World")
            }
            Icon(imageVector = Icons.Default.Face, contentDescription = null)

        },
        measurePolicy = { measurables, constraints ->
            val placeables = measurables.map { it.measure(constraints) }
            layout(
                width = constraints.maxWidth,
                height = constraints.maxHeight,
            ) {
                var lastItemPosition = IntOffset.Zero

                placeables.forEach {
                    it.place(lastItemPosition.x, lastItemPosition.y)
                    lastItemPosition = IntOffset(
                        x = lastItemPosition.x + it.width + itemSpacing.roundToPx(),
                        y = lastItemPosition.y + it.height
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun CustomLayoutExamplePreview() = MaterialTheme {
    CustomLayoutExample()
}