package com.example.domain

import androidx.room.Dao
import com.example.data.User
import com.mouse.core.api.Authentication

@Dao
interface AuthenticationDao : Authentication {
    override suspend fun login(username: String, password: String): User {
        TODO("Not yet implemented")
    }
}