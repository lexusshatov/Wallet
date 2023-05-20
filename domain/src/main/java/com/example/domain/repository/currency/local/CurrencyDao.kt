package com.example.domain.repository.currency.local

import androidx.room.Dao
import androidx.room.Query
import com.example.data.Convert
import com.example.data.Currency
import com.example.data.Rates
import com.example.domain.repository.currency.local.data.RatesEntity
import com.mouse.core.api.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Dao
interface CurrencyDao : CurrencyRepository {

    override fun convert(base: Currency, to: Currency, amount: Double): Flow<Convert> = emptyFlow()

    override fun rates(base: Currency): Flow<Rates> = emptyFlow()

    @Query("SELECT * FROM RatesEntity WHERE base = :base")
    fun selectRates(base: Currency): Flow<RatesEntity>
}