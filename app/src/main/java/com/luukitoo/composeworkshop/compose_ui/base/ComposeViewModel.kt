package com.luukitoo.composeworkshop.compose_ui.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class ComposeViewModel<STATE, EVENT, EFFECT>(initialState: STATE) : ViewModel() {

    var viewState by mutableStateOf(initialState)
        private set

    private val _effects = MutableSharedFlow<EFFECT>()
    val effects get() = _effects.asSharedFlow()

    abstract fun obtainEvent(event: EVENT)

    protected fun updateState(reducer: STATE.() -> STATE) {
        viewState = reducer.invoke(viewState)
    }

    protected suspend fun emitEffect(effect: EFFECT) {
        _effects.emit(effect)
    }
}
