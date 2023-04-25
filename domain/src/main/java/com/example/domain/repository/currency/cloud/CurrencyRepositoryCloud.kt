package com.example.domain.repository.currency.cloud

import com.example.data.Convert
import com.example.data.Currency
import com.example.data.Rates
import com.example.domain.service.CurrencyService
import com.mouse.core.api.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CurrencyRepositoryCloud(
    private val currencyService: CurrencyService,
) : CurrencyRepository {

    override fun convert(base: Currency, to: Currency, amount: Double): Flow<Convert> =
        flow {
            emit(currencyService.convert(base, to, amount))
        }

    override fun rates(base: Currency): Flow<Rates> = flow {
        emit(currencyService.rates(base))
    }
}