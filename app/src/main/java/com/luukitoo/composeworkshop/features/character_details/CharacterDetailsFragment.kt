package com.luukitoo.composeworkshop.features.character_details

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.luukitoo.composeworkshop.compose_ui.base.ComposeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : ComposeFragment<CharacterDetailsViewModel, CharacterDetailsEffect>() {

    override val viewModel by viewModels<CharacterDetailsViewModel>()

    override val content: @Composable () -> Unit = {
        CharacterDetailsScreen(
            state = viewModel.viewState,
            sendEvent = viewModel::obtainEvent
        )
    }

    override fun obtainEffect(effect: CharacterDetailsEffect) {
        when (effect) {
            is CharacterDetailsEffect.GoBack -> findNavController().navigateUp()
        }
    }
}