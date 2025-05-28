package jjh.sample.navigator.main.mvi

import jjh.sample.navigator.base.legacy.State

data class MainState (
    val count: Int = 0,
) : State
