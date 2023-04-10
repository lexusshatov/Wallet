package com.example.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
)

val User.isSignedIn: Boolean
    get() = id != 0