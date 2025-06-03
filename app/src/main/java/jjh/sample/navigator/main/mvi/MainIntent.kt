package jjh.sample.navigator.main.mvi

sealed interface MainIntent  {
    data object Increment : MainIntent
    data object Decrement : MainIntent
}
