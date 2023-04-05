package com.mouse.core.api

import com.example.data.User

interface Authentication {
    suspend fun login(username: String, password: String): User
}