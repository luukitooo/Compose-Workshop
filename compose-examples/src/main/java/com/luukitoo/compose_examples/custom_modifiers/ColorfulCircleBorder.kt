package com.luukitoo.compose_examples.custom_modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.cross(
    color: Color = Color.Black,
    strokeWidth: Dp = 3.dp
) = this then CrossElement(color, strokeWidth)

private data class CrossElement(
    private val color: Color,
    private val strokeWidth: Dp
) : ModifierNodeElement<CrossNode>() {

    override fun create(): CrossNode {
        return CrossNode(color, strokeWidth)
    }

    override fun update(node: CrossNode) {
        node.color = color
        node.strokeWidth = strokeWidth
    }
}

private class CrossNode(
    var color: Color,
    var strokeWidth: Dp
) : Modifier.Node(), DrawModifierNode {

    override fun ContentDrawScope.draw() {
        drawContent()
        drawLine(
            color = color,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = size.height),
            strokeWidth = strokeWidth.value
        )
        drawLine(
            color = color,
            start = Offset(x = 0f, y = size.height),
            end = Offset(x = size.width, y = 0f),
            strokeWidth = strokeWidth.value
        )
    }
}
