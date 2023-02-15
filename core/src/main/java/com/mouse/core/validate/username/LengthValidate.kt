package com.mouse.core.validate.username

import com.mouse.core.validate.ValidateStage

class LengthValidate(
    private val min: Int = 0,
    private val max: Int,
) : ValidateStage<String> {

    override fun validate(value: String): Boolean {
        TODO("Not yet implemented")
    }

    override val error: Throwable
        get() = TODO("Not yet implemented")
}

class LengthLessMinError(min: Int) : Throwable("Length cannot be less than $min")
class LengthExceedsMaxError(max: Int) : Throwable("Length cannot be more than $max")