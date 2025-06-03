package jjh.sample.navigator.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jjh.sample.navigator.main.mvi.MainIntent
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

    fun submitIntent(intent: MainIntent) {
        viewModelScope.launch { intentChannel.send(intent) }
    }

    private fun reduceState(current: MainState, intent: MainIntent): MainState {
        return when (intent) {
            is MainIntent.Increment -> current.copy(count = current.count + 1)
            is MainIntent.Decrement -> current.copy(count = current.count - 1)
        }
    }
}
