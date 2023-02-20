package com.mouse.core.validate

fun interface Validate<T> {
    fun validate(value: T): Result

    data class Result(
        val errors: Map<String, Throwable> = mapOf(),
    ) {
        val isSuccess: Boolean = errors.isEmpty()
    }

    operator fun Result.plus(second: Result): Result {
        return Result(errors + second.errors)
    }
}

class EmptyValidate<T> : Validate<T> {
    override fun validate(value: T) = Validate.Result()
}