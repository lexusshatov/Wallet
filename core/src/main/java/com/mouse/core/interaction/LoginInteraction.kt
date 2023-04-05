package com.mouse.core.interaction

import com.example.data.User
import com.mouse.core.api.Authentication
import com.mouse.core.validate.Validate
import kotlinx.coroutines.CoroutineScope

class LoginInteraction(
    private val authentication: Authentication,
    scope: CoroutineScope,
    loginValidate: Validate<Params>,
) : BaseInteraction<LoginInteraction.Params, User>(scope, validation = loginValidate) {

    override suspend fun process(params: Params): User {
        return authentication.login(params.login, params.password)
    }

    data class Params(
        val login: String,
        val password: String,
    )
}