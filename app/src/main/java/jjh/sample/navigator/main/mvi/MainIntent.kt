package jjh.sample.navigator.main.mvi

import jjh.sample.navigator.base.legacy.Intent

sealed class MainIntent : Intent {
    data object Increment : MainIntent()
    data object Decrement : MainIntent()
}
