package com.mouse.core

import kotlinx.coroutines.flow.Flow

suspend fun <T : Any> Flow<State<T>>.collectState(
    onLoadingChange: (Boolean) -> Unit = {},
    onError: (key: String, error: String) -> Unit = { _, _ -> },
    onSuccess: (T) -> Unit,
) {
    collect { state ->
        when (state) {
            is State.Error -> {
                onLoadingChange(false)
                val stringErrors = state.errors.mapValues { it.value.localizedMessage.orEmpty() }
                stringErrors.forEach { (key, error) ->
                    onError(key, error)
                }
            }
            State.Loading -> onLoadingChange(true)
            is State.Success -> {
                onLoadingChange(false)
                onSuccess(state.result)
            }
        }
    }
}