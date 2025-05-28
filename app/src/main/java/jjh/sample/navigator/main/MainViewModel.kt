package jjh.sample.navigator.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jjh.sample.navigator.base.MviHandlerDelegate
import jjh.sample.navigator.base.MviHandlerImpl
import jjh.sample.navigator.base.Test
import jjh.sample.navigator.main.mvi.MainIntent
import jjh.sample.navigator.main.mvi.MainSideEffect
import jjh.sample.navigator.main.mvi.MainState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() :
    MviHandlerDelegate<MainState, MainSideEffect, MainIntent> by MviHandlerImpl<MainState, MainSideEffect, MainIntent>(
        CoroutineScope(Dispatchers.IO),
        { this },
        ::MainState
    ),
    ViewModel(), Test<MainState, MainIntent> {
    override fun reduceState(state: MainState, event: MainIntent): MainState {
        return when (event) {
            is MainIntent.Increment -> state.copy(count = state.count + 1)
            is MainIntent.Decrement -> state.copy(count = state.count - 1)
        }
    }
}
