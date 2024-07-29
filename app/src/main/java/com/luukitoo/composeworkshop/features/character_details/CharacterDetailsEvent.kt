package com.luukitoo.composeworkshop.features.character_details

sealed interface CharacterDetailsEvent {
    data object OnGoBackClick : CharacterDetailsEvent
}
