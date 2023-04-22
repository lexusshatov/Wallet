package com.example.domain.repository.currency

import com.example.data.Convert
import com.example.data.Currency
import com.example.data.Rates
import com.mouse.core.api.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge

class CurrencyRepositoryDecorator(
    private val currencyRepositoryLocal: CurrencyRepository,
    private val currencyServiceCloud: CurrencyRepository,
) : CurrencyRepository {

    override fun convert(base: Currency, to: Currency, amount: Double): Flow<Convert> =
        merge(
            currencyServiceCloud.convert(base, to, amount),
            currencyRepositoryLocal.convert(base, to, amount)
        )

    override fun rates(base: Currency): Flow<Rates> =
        merge(
            currencyServiceCloud.rates(base),
            currencyRepositoryLocal.rates(base)
        )
}