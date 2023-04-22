package com.example.domain.repository.currency

import com.example.data.Convert
import com.example.data.Currency
import com.example.data.Rates
import com.mouse.core.api.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CurrencyRepositoryLocal : CurrencyRepository {
    override fun convert(base: Currency, to: Currency, amount: Double): Flow<Convert> {
        return flow { emit(Convert(base = base, to = to, converted = 15.5)) }
    }

    override fun rates(base: Currency): Flow<Rates> {
        return flow {
            emit(Rates(base = base, rates = mapOf(Currency.USD to 1.0, Currency.EUR to 0.9109)))
        }
    }
}