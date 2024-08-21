package com.luukitoo.compose_examples.composition_locals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.tooling.preview.Preview

data class UserInfo(
    val name: String,
    val age: Int,
    val email: String
)

val LocalUserInfo = staticCompositionLocalOf<UserInfo> { error("") }

@Composable
fun App() {
    Screen1()

    CompositionLocalProvider(LocalUserInfo provides UserInfo("", 0, "")) {
        Screen2()
    }
}

@Composable
fun Screen1() {

}

@Composable
fun Screen2() {
    Screen2_1()
}

@Composable
fun Screen2_1() {
    LocalUserInfo.current
}

@Preview
@Composable
private fun AppPreview() {
    App()
}