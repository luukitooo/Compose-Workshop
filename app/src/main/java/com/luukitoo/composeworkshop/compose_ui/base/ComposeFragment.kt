package com.luukitoo.composeworkshop.compose_ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.luukitoo.composeworkshop.compose_ui.utils.SharedFlowCollector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class ComposeFragment<VIEW_MODEL : ComposeViewModel<*, *, EFFECT>, EFFECT> : Fragment() {

    abstract val viewModel: VIEW_MODEL

    abstract val content: @Composable () -> Unit

    abstract fun obtainEffect(effect: EFFECT)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(context = requireContext()).apply {
            setViewCompositionStrategy(strategy = ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent { BaseContent() }
        }
    }

    @Composable
    private fun BaseContent() = MaterialTheme {
        Surface {
            content.invoke()
            SharedFlowCollector(
                sharedFlow = viewModel.effects,
                onEmit = ::obtainEffect
            )
        }
    }
}
