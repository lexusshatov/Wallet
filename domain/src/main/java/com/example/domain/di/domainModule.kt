@file:Suppress("RemoveExplicitTypeArguments")

package com.example.domain.di

import com.example.domain.repository.CurrencyRepositoryCloud
import com.example.domain.util.RetrofitUtil
import com.mouse.core.api.CurrencyRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val domainModule = module {
    single<Retrofit> { RetrofitUtil.create() }
    single<CurrencyRepository> { CurrencyRepositoryCloud(get()) }
}