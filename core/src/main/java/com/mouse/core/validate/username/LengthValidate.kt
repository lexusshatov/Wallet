package com.mouse.core.validate.username

import com.mouse.core.validate.ValidateStage

class LengthValidate(
    private val min: Int = 0,
    private val max: Int,
) : ValidateStage<String> {

    init {
        if (min < 0) throw IllegalStateException("Min should be at least 0")
    }

    override fun validate(value: String): Boolean {
        return value.length in min..max
    }

    override val error: Throwable
        get() = when (min) {
            0 -> LengthExceedsMaxError(max)
            else -> LengthOutsideRange(min, max)
        }
}

class LengthOutsideRange(min: Int, max: Int) : Throwable("Length should be between $min and $max")
class LengthExceedsMaxError(max: Int) : Throwable("Length cannot be more than $max")