package com.luukitoo.composeworkshop.rick_and_morty_api.data.service

import com.luukitoo.composeworkshop.rick_and_morty_api.data.model.CharactersListDto
import retrofit2.Response
import retrofit2.http.GET

interface CharactersService {

    @GET("character")
    suspend fun getCharactersList(): Response<CharactersListDto>
}
