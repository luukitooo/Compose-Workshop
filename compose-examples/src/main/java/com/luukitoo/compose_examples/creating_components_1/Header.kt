package com.luukitoo.compose_examples.creating_components_1

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luukitoo.compose_examples.R

@Composable
fun TopToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    headerScope: @Composable BoxScope.() -> Unit = {},
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onBackClick.invoke() },
        ) {
            Icon(painterResource(id = R.drawable.chevron_left_outlined), "")
        }
        Box(
            modifier = Modifier.weight(1f)
        ) {
            headerScope()
        }
        IconButton(
            onClick = { onMenuClick.invoke() },
        ) {
            Icon(painterResource(id = R.drawable.kebab_menu_vertical_outlined), "")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HeaderPreview() {
    TopToolbar(
        onBackClick = {},
        onMenuClick = {},
        headerScope = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(ImageVector.vectorResource(id = R.drawable.tbc_filled), "TBC Logo")
                Text("TBC")
            }
        }
    )
}