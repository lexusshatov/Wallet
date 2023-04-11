package com.example.domain.service

import com.example.data.Convert
import com.example.data.Currency
import com.example.data.Rates
import com.mouse.core.api.CurrencyRepository
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService : CurrencyRepository {

    @GET("convert")
    override suspend fun convert(
        @Query("base") base: Currency,
        @Query("to") to: Currency,
        @Query("amount") amount: Double,
    ): Convert

    @GET("rates")
    override suspend fun rates(
        @Query("base") base: Currency,
    ): Rates
}