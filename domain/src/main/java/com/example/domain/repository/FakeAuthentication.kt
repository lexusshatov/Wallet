package com.example.domain.repository

import com.example.data.User
import com.mouse.core.api.Authentication
import kotlinx.coroutines.delay
import kotlin.random.Random

class FakeAuthentication : Authentication {
    override suspend fun login(username: String, password: String): User {
        delay(2000)
        return if (Random.nextBoolean()) {
            User(firstName = "Oleh", lastName = "Shatov")
        } else throw InvalidCredentialsError()
    }
}

class InvalidCredentialsError : Exception("Invalid login/password")