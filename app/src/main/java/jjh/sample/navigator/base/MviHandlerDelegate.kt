package jjh.sample.navigator.base

import kotlinx.coroutines.flow.StateFlow

interface MviHandlerDelegate<STATE, SIDE_EFFECT, INTENT> {
    val state: StateFlow<STATE>
    fun submitIntent(intent: INTENT)
//    fun reduceState(current: STATE, intent: INTENT): STATE
}
