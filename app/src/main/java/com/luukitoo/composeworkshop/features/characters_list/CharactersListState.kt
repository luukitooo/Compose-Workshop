package com.luukitoo.composeworkshop.features.characters_list

import com.luukitoo.composeworkshop.rick_and_morty_api.data.model.CharacterStatus
import com.luukitoo.composeworkshop.rick_and_morty_api.domain.model.CharactersList
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class CharactersListState(
    val characters: ImmutableList<CharactersList.CharacterItem> = emptyList<CharactersList.CharacterItem>().toImmutableList(),
    val currentFilter: CharacterStatus = CharacterStatus.ALL,
    val isLoading: Boolean = false,
    val error: Throwable? = null
)
