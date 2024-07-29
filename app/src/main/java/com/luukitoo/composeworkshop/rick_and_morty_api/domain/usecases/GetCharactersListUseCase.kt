package com.luukitoo.composeworkshop.rick_and_morty_api.domain.usecases

import com.luukitoo.composeworkshop.rick_and_morty_api.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharactersListUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    suspend fun execute() = repository.getCharactersList()
}
