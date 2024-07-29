package com.luukitoo.composeworkshop.features.character_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.luukitoo.composeworkshop.compose_ui.base.ComposeViewModel
import com.luukitoo.composeworkshop.rick_and_morty_api.domain.usecases.GetCharacterDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ComposeViewModel<CharacterDetailsState, CharacterDetailsEvent, CharacterDetailsEffect>(
    initialState = CharacterDetailsState()
) {

    init {
        getDetails()
    }

    override fun obtainEvent(event: CharacterDetailsEvent) {
        when (event) {
            is CharacterDetailsEvent.OnGoBackClick -> goBack()
        }
    }

    private fun goBack() = viewModelScope.launch {
        emitEffect(CharacterDetailsEffect.GoBack)
    }

    private fun getDetails() = viewModelScope.launch {
        updateState { copy(isLoading = true, error = null) }
        getCharacterDetailsUseCase.execute(
            characterId = savedStateHandle.get<Long>("characterId") ?: -1
        ).onSuccess { character ->
            updateState { copy(character = character, isLoading = false) }
        }.onFailure { throwable ->
            updateState { copy(error = throwable, isLoading = false) }
        }
    }
}