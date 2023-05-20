package com.example.domain.repository

import com.example.data.Currency
import com.example.data.User
import com.mouse.core.api.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeUserRepository : UserRepository {

    override val user: User = if (true) {
        User(id = 1)
    } else User(id = 0)

    private val _coinsCurrency = MutableStateFlow(Currency.USD)
    override val coinsCurrency: Flow<Currency> = _coinsCurrency

    override fun setCoinsCurrency(currency: Currency) {
        _coinsCurrency.value = currency
    }
}