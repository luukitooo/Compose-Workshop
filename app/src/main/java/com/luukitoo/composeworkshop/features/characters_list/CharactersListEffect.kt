package com.luukitoo.composeworkshop.features.characters_list

interface CharactersListEffect {

    data class ShowErrorToast(val error: Throwable) : CharactersListEffect

    data class GoToDetailsScreen(val characterId: Long) : CharactersListEffect
}
