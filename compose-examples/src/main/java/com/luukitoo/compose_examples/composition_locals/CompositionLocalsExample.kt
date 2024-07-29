@file:Suppress("UNREACHABLE_CODE")

package com.luukitoo.compose_examples.composition_locals

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

val LocalUserInfo = staticCompositionLocalOf { UserInfoValues() }

@Composable
fun WithUserInfo(userInfoValues: UserInfoValues, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalUserInfo provides userInfoValues) {
        content.invoke()
    }
}

@Composable
fun CompositionLocalsExample() {

    val receivedUserInfoValues = remember { UserInfoValues() }

    WithUserInfo(receivedUserInfoValues) {
        SomeScreen()
    }
}

@Composable
fun SomeScreen() {
    UserSection()
}

@Composable
fun UserSection() {
    UserInfoItem(paddingValues = TODO())
}

@Composable
fun UserInfoItem(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
) {
    Card(modifier = modifier) {
        // Using Parameters Here...
        LocalUserInfo.current
    }
}

data class UserInfoValues(
    val username: String? = null,
    val age: Int? = null,
    val address: String? = null,
    val email: String? = null,
    val phone: String? = null,
)