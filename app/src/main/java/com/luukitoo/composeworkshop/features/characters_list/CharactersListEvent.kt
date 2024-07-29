package com.luukitoo.composeworkshop.features.characters_list

import com.luukitoo.composeworkshop.rick_and_morty_api.data.model.CharacterStatus
import com.luukitoo.composeworkshop.rick_and_morty_api.domain.model.CharactersList

interface CharactersListEvent {

    data object OnGetCharactersRequested : CharactersListEvent

    data class OnFilterSelected(val characterStatus: CharacterStatus) : CharactersListEvent

    data class OnCharacterClicked(val character: CharactersList.CharacterItem) : CharactersListEvent
}
