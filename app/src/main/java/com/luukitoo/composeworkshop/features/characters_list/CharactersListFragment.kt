package com.luukitoo.composeworkshop.features.characters_list

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.luukitoo.composeworkshop.compose_ui.base.ComposeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersListFragment : ComposeFragment<CharactersListViewModel, CharactersListEffect>() {

    override val viewModel by viewModels<CharactersListViewModel>()

    override val content: @Composable () -> Unit = {
        CharactersListScreen(
            state = viewModel.viewState,
            sendEvent = viewModel::obtainEvent
        )
    }

    override fun obtainEffect(effect: CharactersListEffect) {
        when (effect) {
            is CharactersListEffect.ShowErrorToast -> showErrorToast(effect.error)
            is CharactersListEffect.GoToDetailsScreen -> goToDetailsScreen(effect.characterId)
        }
    }

    private fun goToDetailsScreen(characterId: Long) {
        findNavController().navigate(
            CharactersListFragmentDirections.toCharacterDetailsFragment(
                characterId = characterId
            )
        )
    }

    private fun showErrorToast(error: Throwable) {
        Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
    }
}
