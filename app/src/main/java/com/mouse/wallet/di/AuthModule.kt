package com.mouse.wallet.di

import com.example.domain.repository.FakeAuthenticationRepository
import com.mouse.core.api.AuthenticationRepository
import com.mouse.core.interaction.LoginInteraction
import com.mouse.core.validate.Validate
import com.mouse.core.validate.login.LoginValidate
import com.mouse.wallet.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authModule = module {
    singleOf<AuthenticationRepository>(::FakeAuthenticationRepository)

    factoryOf<Validate<LoginInteraction.Params>>(::LoginValidate)
    factoryOf(::LoginInteraction)

    viewModelOf(::AuthViewModel)
}