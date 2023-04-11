package com.example.domain.repository

import com.example.domain.service.CurrencyService
import com.mouse.core.api.CurrencyRepository
import retrofit2.Retrofit

class CurrencyRepositoryCloud(
    retrofit: Retrofit,
) : CurrencyRepository by retrofit.create(CurrencyService::class.java)