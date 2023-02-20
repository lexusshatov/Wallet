package com.mouse.core.validate

fun interface Validate<T> {
    fun validate(value: T): Result

    data class Result(
        val errors: Map<String, Throwable> = mapOf(),
    ) {
        val isSuccess: Boolean = errors.isEmpty()
    }
}

open class BaseValidate<T>(private val validates: List<ValidateStage<T>>) : Validate<T> {

    constructor(vararg validates: ValidateStage<T>) : this(validates.toList())

    override fun validate(value: T): Validate.Result {
        val errors = mutableMapOf<String, Throwable>()
        validates.forEach { stage ->
            if (!stage.validate(value)) {
                errors += stage.key to stage.error
            }
        }
        return Validate.Result(errors)
    }
}

class EmptyValidate<T> : Validate<T> {
    override fun validate(value: T) = Validate.Result()
}