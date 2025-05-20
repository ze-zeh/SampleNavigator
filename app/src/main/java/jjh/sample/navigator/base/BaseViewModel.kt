package jjh.sample.navigator.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jjh.sample.navigator.main.mvi.MainEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel<S : State, SE : SideEffect, I : Event>() : ViewModel() {
    protected val events = Channel<MainEvent>()

    protected abstract suspend fun onEvent(event: I)

    fun intent(intent: I) {
        launch {
            onEvent(intent)
        }
    }

    private inline fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        crossinline action: suspend CoroutineScope.() -> Unit,
    ): Job {
        return viewModelScope.launch(context, start = start) {
            action()
        }
    }
}
