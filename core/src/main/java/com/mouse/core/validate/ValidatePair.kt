package com.mouse.core.validate

data class ValidatePair<T, E>(
    val stage: ValidateStage<E>,
    val getElement: (T) -> E,
)

public infix fun <T, E> ValidateStage<E>.from(getElement: (T) -> E) = ValidatePair(this, getElement)