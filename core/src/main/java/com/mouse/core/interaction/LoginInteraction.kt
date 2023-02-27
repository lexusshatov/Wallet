package com.mouse.core.interaction

import com.example.data.User
import com.mouse.core.Authentication
import com.mouse.core.validate.login.LoginValidate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class LoginInteraction(
    private val authentication: Authentication,
    scope: CoroutineScope = MainScope(),
    loginValidate: LoginValidate = LoginValidate(),
) : BaseInteraction<LoginInteraction.Params, User>(scope, validation = loginValidate) {

    override suspend fun process(params: Params): User {
        return authentication.login(params.login, params.password)
    }

    data class Params(
        val login: String,
        val password: String,
    )
}