package com.mouse.wallet.di

import com.example.domain.repository.FakeUserRepository
import com.mouse.core.api.UserRepository
import com.mouse.wallet.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mainModule = module {
    single { CoroutineScope(Dispatchers.IO + SupervisorJob()) }

    singleOf<UserRepository>(::FakeUserRepository)
    viewModelOf(::UserViewModel)
}