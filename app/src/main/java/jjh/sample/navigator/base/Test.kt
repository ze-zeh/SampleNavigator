package jjh.sample.navigator.base

interface Test<STATE, INTENT> {
    fun reduceState(current: STATE, intent: INTENT): STATE
}
