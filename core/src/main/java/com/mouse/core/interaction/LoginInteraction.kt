package com.mouse.core.interaction

import com.mouse.core.data.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay

class LoginInteraction(
    scope: CoroutineScope = MainScope(),
) : BaseInteraction<LoginInteraction.Params, User>(scope) {

    override suspend fun process(params: Params): User {
        delay(2000)
        return User(firstName = "Oleh", lastName = "Shatov")
    }

    data class Params(
        val login: String,
        val password: String,
    )
}