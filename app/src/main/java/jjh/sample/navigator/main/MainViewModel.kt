package jjh.sample.navigator.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jjh.sample.navigator.base.BaseViewModel
import jjh.sample.navigator.main.mvi.MainIntent
import jjh.sample.navigator.main.mvi.MainSideEffect
import jjh.sample.navigator.main.mvi.MainState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel<MainState, MainSideEffect, MainIntent>() {
    val state = events.receiveAsFlow()
        .runningFold(MainState(), ::reduceState)
        .stateIn(viewModelScope, SharingStarted.Eagerly, MainState())

    private fun reduceState(state: MainState, event: MainIntent): MainState {
        return when (event) {
            is MainIntent.Increment -> state.copy(count = state.count + 1)
            is MainIntent.Decrement -> state.copy(count = state.count - 1)
        }
    }
}