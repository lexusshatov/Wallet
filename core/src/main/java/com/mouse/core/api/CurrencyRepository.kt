package com.mouse.core.api

import com.example.data.Convert
import com.example.data.Currency
import com.example.data.Rates
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun convert(base: Currency, to: Currency, amount: Double = 1.0): Flow<Convert>
    fun rates(base: Currency): Flow<Rates>
}