package com.mouse.core.validate

interface ValidateStage<T> {
    fun validate(value: T): Boolean
    val error: Throwable
}