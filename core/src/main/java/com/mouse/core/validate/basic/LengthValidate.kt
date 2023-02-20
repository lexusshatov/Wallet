package com.mouse.core.validate.basic

import com.mouse.core.validate.SimpleValidate

class LengthValidate(
    private val min: Int = 0,
    private val max: Int,
    key: String,
) : SimpleValidate<String>(key) {

    init {
        if (min < 0) throw IllegalStateException("Min should be at least 0")
    }

    override val error = when (min) {
        0 -> LengthExceedsMaxError(max)
        else -> LengthOutsideRange(min, max)
    }

    override fun isValid(value: String): Boolean {
        return value.length in min..max
    }
}

class LengthOutsideRange(min: Int, max: Int) : Throwable("Length should be between $min and $max")
class LengthExceedsMaxError(max: Int) : Throwable("Length cannot be more than $max")