package com.luukitoo.composeworkshop.features.characters_list

import androidx.lifecycle.viewModelScope
import com.luukitoo.composeworkshop.compose_ui.base.ComposeViewModel
import com.luukitoo.composeworkshop.rick_and_morty_api.data.model.CharacterStatus
import com.luukitoo.composeworkshop.rick_and_morty_api.domain.model.CharactersList
import com.luukitoo.composeworkshop.rick_and_morty_api.domain.usecases.GetCharactersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase
) : ComposeViewModel<CharactersListState, CharactersListEvent, CharactersListEffect>(
    initialState = CharactersListState()
) {

    init {
        getCharacters()
    }

    override fun obtainEvent(event: CharactersListEvent) {
        when (event) {
            is CharactersListEvent.OnGetCharactersRequested -> getCharacters()
            is CharactersListEvent.OnFilterSelected -> updateFilter(event.characterStatus)
            is CharactersListEvent.OnCharacterClicked -> goToDetailsScreen(event.character.id)
        }
    }

    private fun goToDetailsScreen(id: Long?) {
        viewModelScope.launch {
            emitEffect(CharactersListEffect.GoToDetailsScreen(id ?: -1))
        }
    }

    private fun updateFilter(characterStatus: CharacterStatus) {
        updateState { copy(currentFilter = characterStatus) }
        getCharacters()
    }

    private fun getCharacters() = viewModelScope.launch {
        updateState { copy(isLoading = true) }
        getCharactersListUseCase.execute()
            .onSuccess { charactersList ->
                val finalCharacters = if (viewState.currentFilter != CharacterStatus.ALL) {
                    charactersList.results.orEmpty().filter {
                        it.status == viewState.currentFilter
                    }
                } else {
                    charactersList.results.orEmpty()
                }
                updateState {
                    copy(
                        characters = finalCharacters.toImmutableList(),
                        isLoading = false
                    )
                }
            }.onFailure { throwable ->
                updateState {
                    copy(
                        error = throwable,
                        isLoading = false
                    )
                }
            }
    }
}
