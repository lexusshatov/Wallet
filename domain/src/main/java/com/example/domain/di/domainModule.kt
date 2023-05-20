@file:Suppress("RemoveExplicitTypeArguments")

package com.example.domain.di

import androidx.room.Room
import com.example.domain.repository.currency.CurrencyRepositoryDecorator
import com.example.domain.repository.currency.cloud.CurrencyRepositoryCloud
import com.example.domain.repository.currency.local.CurrencyDao
import com.example.domain.repository.currency.local.CurrencyDatabase
import com.example.domain.service.CurrencyService
import com.example.domain.util.RetrofitUtil
import com.mouse.core.api.CurrencyRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val domainModule = module {
    single<Retrofit> { RetrofitUtil.create() }
    single<CurrencyService> {
        val retrofit: Retrofit = get()
        retrofit.create(CurrencyService::class.java)
    }

    single<CurrencyDatabase> {
        Room.databaseBuilder(
            context = androidContext(),
            klass = CurrencyDatabase::class.java,
            name = "CurrencyDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single<CurrencyDao> { get<CurrencyDatabase>().getDao() }

    single<CurrencyRepository>(named("Local")) { get<CurrencyDao>() }
    single<CurrencyRepository>(named("Cloud")) { CurrencyRepositoryCloud(get()) }
    single<CurrencyRepository> {
        CurrencyRepositoryDecorator(
            currencyRepositoryLocal = get(named("Local")),
            currencyServiceCloud = get(named("Cloud"))
        )
    }
}