package com.mouse.core.api

import com.example.data.User

interface AuthenticationRepository {
    suspend fun login(username: String, password: String): User
}