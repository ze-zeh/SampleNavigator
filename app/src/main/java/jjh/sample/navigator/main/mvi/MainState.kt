package jjh.sample.navigator.main.mvi

data class MainState(
    val isLoading: Boolean = true,
    val count: Int = 0,
    val isError: Boolean = false,
)
