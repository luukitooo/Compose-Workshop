package com.luukitoo.composeworkshop.rick_and_morty_api.domain.usecases

import com.luukitoo.composeworkshop.rick_and_morty_api.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    suspend fun execute(characterId: Long) = runCatching {
        val characters = repository.getCharactersList().getOrNull()
        characters?.results?.find {
            it.id == characterId
        }!!
    }
}
