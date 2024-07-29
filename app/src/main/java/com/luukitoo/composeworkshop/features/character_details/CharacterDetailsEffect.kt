package com.luukitoo.composeworkshop.features.character_details

sealed interface CharacterDetailsEffect {
    data object GoBack : CharacterDetailsEffect
}