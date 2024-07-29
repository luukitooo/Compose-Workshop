package com.luukitoo.composeworkshop.features.characters_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilterChip
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.luukitoo.composeworkshop.compose_ui.components.CharacterListItem
import com.luukitoo.composeworkshop.compose_ui.components.CharacterListItemValues
import com.luukitoo.composeworkshop.compose_ui.components.ScrollUpButton
import com.luukitoo.composeworkshop.rick_and_morty_api.data.model.CharacterStatus
import com.luukitoo.composeworkshop.rick_and_morty_api.domain.model.CharactersList
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersListScreen(
    state: CharactersListState,
    sendEvent: (CharactersListEvent) -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text(text = "Characters") },
                scrollBehavior = scrollBehavior,
            )
        },
        floatingActionButton = {
            ScrollUpButton(text = { Text(text = "Scroll Up") }, scrollState = lazyListState)
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 80.dp)
        ) {
            characterFilters(
                selectedFilter = state.currentFilter,
                onFilterSelected = { sendEvent(CharactersListEvent.OnFilterSelected(it)) }
            )
            characterItems(
                charactersList = state.characters,
                onCharacterClick = { sendEvent(CharactersListEvent.OnCharacterClicked(it)) }
            )
        }
    }
}

private fun LazyListScope.characterFilters(
    selectedFilter: CharacterStatus,
    onFilterSelected: (CharacterStatus) -> Unit
) = item {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(CharacterStatus.entries, key = { it.ordinal }) { characterStatus ->
            FilterChip(
                selected = selectedFilter == characterStatus,
                onClick = { onFilterSelected(characterStatus) },
                label = { Text(text = characterStatus.name) }
            )
        }
    }
}

private fun LazyListScope.characterItems(
    charactersList: ImmutableList<CharactersList.CharacterItem>,
    onCharacterClick: (CharactersList.CharacterItem) -> Unit,
) = items(charactersList, key = { it.id ?: -1 }) { character ->
    CharacterListItem(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 18.dp),
        values = CharacterListItemValues(
            name = character.name ?: "",
            status = character.status ?: CharacterStatus.UNKNOWN,
            image = character.image ?: ""
        ),
        onClick = { onCharacterClick.invoke(character) }
    )
}
