package com.luukitoo.composeworkshop.rick_and_morty_api.data.model

import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName
import com.luukitoo.composeworkshop.rick_and_morty_api.domain.model.CharactersList

data class CharactersListDto(
    @SerializedName("results")
    val results: List<CharacterItemDto>? = null
) {

    data class CharacterItemDto(
        @SerializedName("id")
        val id: Long? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("status")
        val status: CharacterStatus? = null,
        @SerializedName("image")
        val image: String? = null
    )
}

enum class CharacterStatus(val color: Color) {

    ALL(color = Color.Transparent),

    @SerializedName("Alive")
    ALIVE(color = Color.Green),

    @SerializedName("Dead")
    DEAD(color = Color.Red),

    @SerializedName("unknown")
    UNKNOWN(color = Color.Gray)
}

fun CharactersListDto.toDomain() = CharactersList(
    results = results?.map {
        CharactersList.CharacterItem(
            id = it.id,
            name = it.name,
            status = it.status,
            image = it.image
        )
    }
)