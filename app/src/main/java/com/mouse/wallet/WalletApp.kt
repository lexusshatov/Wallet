package com.mouse.wallet

import android.app.Application
import com.mouse.wallet.di.authModule
import com.mouse.wallet.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WalletApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WalletApp)
            modules(
                mainModule,
                authModule
            )
        }
    }
}