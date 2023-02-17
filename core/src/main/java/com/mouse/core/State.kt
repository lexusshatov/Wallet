package com.mouse.core

import kotlinx.coroutines.flow.Flow

sealed class State<out T : Any> {
    object Loading : State<Nothing>()
    data class Success<T : Any>(val result: T) : State<T>()
    data class Error(val message: String) : State<Nothing>()
}

suspend fun <T : Any> Flow<State<T>>.collectState(
    onLoading: (Boolean) -> Unit = {},
    onError: (String) -> Unit = {},
    onSuccess: (T) -> Unit = {},
) {
    collect { state ->
        when (state) {
            is State.Error -> {
                onLoading(false)
                onError(state.message)
            }
            State.Loading -> onLoading(true)
            is State.Success -> {
                onLoading(false)
                onSuccess(state.result)
            }
        }
    }
}