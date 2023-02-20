package com.mouse.core

import kotlinx.coroutines.flow.Flow

sealed class State<out T : Any> {
    object Loading : State<Nothing>()
    data class Success<T : Any>(val result: T) : State<T>()
    data class Error(val errors: Map<String, Throwable>) : State<Nothing>()
}