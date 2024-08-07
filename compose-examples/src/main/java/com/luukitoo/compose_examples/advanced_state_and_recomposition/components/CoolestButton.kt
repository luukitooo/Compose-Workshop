package com.luukitoo.compose_examples.advanced_state_and_recomposition.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CoolestButton(
    text: String,
    type: CoolestButtonType,
    onClick: () -> Unit,
    shape: Shape = ButtonDefaults.shape
) {

    val buttonColors = when (type) {
        CoolestButtonType.PRIMARY -> CoolestButtonColors.primaryColors()
        CoolestButtonType.SECONDARY -> CoolestButtonColors.secondaryColors()
        CoolestButtonType.TERTIARY -> CoolestButtonColors.tertiaryColors()
    }

    val interactionSource = remember { MutableInteractionSource() }
    val isButtonPressed by interactionSource.collectIsPressedAsState()
    val buttonRotation by animateFloatAsState(if (isButtonPressed) 360f else 0f)

    Button(
        modifier = Modifier.rotate(buttonRotation),
        onClick = onClick,
        interactionSource = interactionSource,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColors.backgroundColor,
            contentColor = buttonColors.contentColor
        )
    ) {
        Text(text = text)
    }
}

enum class CoolestButtonType {
    PRIMARY, SECONDARY, TERTIARY
}

class CoolestButtonColors(
    val backgroundColor: Color,
    val contentColor: Color
) {
    companion object {
        fun primaryColors() = CoolestButtonColors(
            backgroundColor = Color.Blue,
            contentColor = Color.White
        )

        fun secondaryColors() = CoolestButtonColors(
            backgroundColor = Color.Cyan,
            contentColor = Color.Black
        )

        fun tertiaryColors() = CoolestButtonColors(
            backgroundColor = Color.Magenta,
            contentColor = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CoolestButtonPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CoolestButton(
            text = "Click Me PLEASE!",
            type = CoolestButtonType.PRIMARY,
            onClick = { }
        )
    }
}
