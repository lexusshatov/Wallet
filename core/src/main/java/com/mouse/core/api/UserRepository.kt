package com.mouse.core.api

import com.example.data.Currency
import com.example.data.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val user: User
    val coinsCurrency: Flow<Currency>
    fun setCoinsCurrency(currency: Currency)
}