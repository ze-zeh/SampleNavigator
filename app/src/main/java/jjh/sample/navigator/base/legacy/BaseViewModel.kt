package jjh.sample.navigator.base.legacy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : State, SE : SideEffect, I : Intent>() : ViewModel() {
    protected val events = Channel<I>()

    fun onEvent(intent: I) {
        viewModelScope.launch {
            events.send(intent)
        }
    }
}
