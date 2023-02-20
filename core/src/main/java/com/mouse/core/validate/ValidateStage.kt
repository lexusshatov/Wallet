package com.mouse.core.validate

interface ValidateStage<T> {
    val key: String
    fun validate(value: T): Boolean
    val error: Throwable
}