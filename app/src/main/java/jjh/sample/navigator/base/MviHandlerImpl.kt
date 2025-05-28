package jjh.sample.navigator.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MviHandlerImpl<STATE, SIDE_EFFECT, INTENT>(
    private val viewModelScope: CoroutineScope,
    testtest:()->Test<STATE,INTENT>,
    initialState: () -> STATE
) : MviHandlerDelegate<STATE, SIDE_EFFECT, INTENT> {

    private val intents = Channel<INTENT>()

    override fun submitIntent(intent: INTENT) {
        viewModelScope.launch {
            intents.send(intent)
        }
    }

    override val state = intents.receiveAsFlow()
        .runningFold(initialState(), testtest()::reduceState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            initialState()
        )
}
