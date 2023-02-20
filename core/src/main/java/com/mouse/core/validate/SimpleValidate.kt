package com.mouse.core.validate

abstract class SimpleValidate<T>(private val key: String) : Validate<T> {

    protected abstract fun isValid(value: T): Boolean
    protected abstract val error: Throwable

    override fun validate(value: T): Validate.Result {
        val errors: Map<String, Throwable> = if (isValid(value)) {
            emptyMap()
        } else mapOf(key to error)
        return Validate.Result(errors)
    }
}