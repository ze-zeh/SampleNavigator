package jjh.sample.navigator.main.mvi

sealed class MainIntent  {
    data object Increment : MainIntent()
    data object Decrement : MainIntent()
}
