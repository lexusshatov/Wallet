@file:Suppress("RemoveExplicitTypeArguments")

package com.example.domain.di

import com.example.domain.repository.currency.cloud.CurrencyRepositoryCloud
import com.example.domain.repository.currency.CurrencyRepositoryDecorator
import com.example.domain.repository.currency.local.CurrencyRepositoryLocal
import com.example.domain.service.CurrencyService
import com.example.domain.util.RetrofitUtil
import com.mouse.core.api.CurrencyRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val domainModule = module {
    single<Retrofit> { RetrofitUtil.create() }
    single<CurrencyService> {
        val retrofit: Retrofit = get()
        retrofit.create(CurrencyService::class.java)
    }
    single<CurrencyRepository>(named("Local")) { CurrencyRepositoryLocal() }
    single<CurrencyRepository>(named("Cloud")) { CurrencyRepositoryCloud(get()) }
    single<CurrencyRepository> {
        CurrencyRepositoryDecorator(
            currencyRepositoryLocal = get(named("Local")),
            currencyServiceCloud = get(named("Cloud"))
        )
    }
}