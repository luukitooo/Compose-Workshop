package com.luukitoo.composeworkshop.features.character_details

import com.luukitoo.composeworkshop.rick_and_morty_api.domain.model.CharactersList

data class CharacterDetailsState(
    val character: CharactersList.CharacterItem? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null
)
