package com.mouse.wallet.di

import com.mouse.wallet.viewmodel.CurrencyViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val currencyModule = module {
    viewModelOf(::CurrencyViewModel)
}