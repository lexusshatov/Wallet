package com.mouse.wallet.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val mainModule = module {
    single { CoroutineScope(Dispatchers.IO + SupervisorJob()) }
}