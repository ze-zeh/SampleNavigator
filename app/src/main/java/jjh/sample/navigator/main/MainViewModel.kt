package jjh.sample.navigator.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jjh.sample.navigator.main.mvi.MainIntent
import jjh.sample.navigator.main.mvi.MainSideEffect
import jjh.sample.navigator.main.mvi.MainState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val intentChannel = Channel<MainIntent>()

    val state = intentChannel.receiveAsFlow()
        .runningFold(MainState(), ::reduceState)
        .stateIn(viewModelScope, SharingStarted.Eagerly, MainState())

    private val _sideEffectChannel = Channel<MainSideEffect>(Channel.BUFFERED)
    val sideEffectFlow = _sideEffectChannel.receiveAsFlow()

    fun submitIntent(intent: MainIntent) {
        viewModelScope.launch { intentChannel.send(intent) }
    }

    private fun reduceState(current: MainState, intent: MainIntent): MainState {
        return when (intent) {
            is MainIntent.Increment -> incrementIntentListener(current)
            is MainIntent.Decrement -> decrementIntentListener(current)
        }
    }

    private fun sendSideEffect(sideEffect: MainSideEffect) {
        viewModelScope.launch { _sideEffectChannel.send(sideEffect) }
    }

    private fun incrementIntentListener(current: MainState): MainState {
        showChangeMessage("늘어난다 히히")
        return current.copy(count = current.count + 1)
    }

    private fun decrementIntentListener(current: MainState): MainState{
        showChangeMessage("줄어든다 히히")
        return current.copy(count = current.count - 1)
    }

    private fun showChangeMessage(message: String) {
        sendSideEffect(MainSideEffect.ShowChangeMessage(message))
    }
}
