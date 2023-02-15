package com.mouse.core.validate

fun interface Validate<T> {
    fun validate(value: T): Result

    data class Result(
        val errors: List<String> = emptyList(),
    ) {
        val isSuccess: Boolean = errors.isEmpty()
    }
}

class EmptyValidate<T> : Validate<T> {
    override fun validate(value: T) = Validate.Result()
}