package com.mouse.core.api

import com.example.data.Convert
import com.example.data.Currency
import com.example.data.Rates

interface CurrencyRepository {
    suspend fun convert(base: Currency, to: Currency, amount: Double = 1.0): Convert
    suspend fun rates(base: Currency): Rates
}