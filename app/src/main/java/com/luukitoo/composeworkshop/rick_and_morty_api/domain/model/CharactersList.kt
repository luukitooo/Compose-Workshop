package com.luukitoo.composeworkshop.rick_and_morty_api.domain.model

import com.luukitoo.composeworkshop.rick_and_morty_api.data.model.CharacterStatus

data class CharactersList(
    val results: List<CharacterItem>? = null
) {

    data class CharacterItem(
        val id: Long? = null,
        val name: String? = null,
        val status: CharacterStatus? = null,
        val image: String? = null
    )
}
