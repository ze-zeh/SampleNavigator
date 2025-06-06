package jjh.sample.navigator.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jjh.sample.navigator.common.BaseViewModel
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

/**
 * state ->loading/error 상황은 처리 생략
 * */
@HiltViewModel
class MainViewModel @Inject constructor() :
    BaseViewModel<MainIntent, MainState, MainSideEffect>(::MainState) {

    override fun reduceState(current: MainState, intent: MainIntent): MainState {
        return when (intent) {
            is MainIntent.Increment -> incrementIntentListener(current)
            is MainIntent.Decrement -> decrementIntentListener(current)
        }
    }

    private fun incrementIntentListener(current: MainState): MainState {
        showChangeMessage("늘어난다 히히")
        return current.copy(count = current.count + 1)
    }

    private fun decrementIntentListener(current: MainState): MainState {
        showChangeMessage("줄어든다 히히")
        return current.copy(count = current.count - 1)
    }

    private fun showChangeMessage(message: String) {
        sendSideEffect(MainSideEffect.ShowChangeMessage(message))
    }
}
