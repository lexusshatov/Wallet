package com.example.domain.repository

import com.example.data.User
import com.mouse.core.api.UserRepository
import kotlin.random.Random

class FakeUserRepository : UserRepository {
    override val user: User = if (Random.nextBoolean()) {
        User(id = 1)
    } else User(id = 0)
}