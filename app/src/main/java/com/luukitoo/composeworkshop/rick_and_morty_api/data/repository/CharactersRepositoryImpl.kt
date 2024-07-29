package com.luukitoo.composeworkshop.rick_and_morty_api.data.repository

import com.luukitoo.composeworkshop.rick_and_morty_api.data.model.CharactersListDto
import com.luukitoo.composeworkshop.rick_and_morty_api.data.model.toDomain
import com.luukitoo.composeworkshop.rick_and_morty_api.data.service.CharactersService
import com.luukitoo.composeworkshop.rick_and_morty_api.domain.model.CharactersList
import com.luukitoo.composeworkshop.rick_and_morty_api.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val service: CharactersService
) : CharactersRepository {

    override suspend fun getCharactersList(): Result<CharactersList> {
        return runCatching {
            service.getCharactersList().body()!!
        }.map(CharactersListDto::toDomain)
    }
}
