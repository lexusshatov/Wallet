package com.mouse.core.validate.login

import com.mouse.core.interaction.LoginInteraction
import com.mouse.core.validate.Validate
import com.mouse.core.validate.basic.LengthValidate

class LoginValidate : Validate<LoginInteraction.Params> {

    private val loginLength = LengthValidate(min = LOGIN_MIN, max = LOGIN_MAX, key = LOGIN_KEY)
    private val passwordLength =
        LengthValidate(min = PASSWORD_MIN, max = PASSWORD_MAX, key = PASSWORD_KEY)

    override fun validate(value: LoginInteraction.Params): Validate.Result {
        return loginLength.validate(value.login) +
                passwordLength.validate(value.password)
    }

    companion object {
        const val LOGIN_KEY = "login"
        private const val LOGIN_MIN = 3
        private const val LOGIN_MAX = 20

        const val PASSWORD_KEY = "password"
        private const val PASSWORD_MIN = 8
        private const val PASSWORD_MAX = 25
    }
}