package com.luukitoo.composeworkshop.rick_and_morty_api.domain.repository

import com.luukitoo.composeworkshop.rick_and_morty_api.domain.model.CharactersList

interface CharactersRepository {

    suspend fun getCharactersList(): Result<CharactersList>
}
