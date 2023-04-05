package com.example.domain.repository

import com.example.data.User
import com.mouse.core.api.Authentication
import kotlinx.coroutines.delay

class FakeAuthentication : Authentication {
    override suspend fun login(username: String, password: String): User {
        delay(2000)
        return User(firstName = "Oleh", lastName = "Shatov")
    }
}