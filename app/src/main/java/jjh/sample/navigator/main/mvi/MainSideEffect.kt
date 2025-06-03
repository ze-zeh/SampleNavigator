package jjh.sample.navigator.main.mvi

sealed interface MainSideEffect  {
    data class ShowChangeMessage(val message: String) : MainSideEffect
}
