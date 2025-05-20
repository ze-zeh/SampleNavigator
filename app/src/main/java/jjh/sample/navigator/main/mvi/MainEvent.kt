package jjh.sample.navigator.main.mvi

import jjh.sample.navigator.base.Event

sealed class MainEvent : Event {
    data object Increment : MainEvent()
    data object Decrement : MainEvent()
}