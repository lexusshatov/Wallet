package com.mouse.wallet.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.Currency
import com.example.data.Rates
import com.mouse.core.api.CurrencyRepository
import com.mouse.core.api.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class CurrencyViewModel(
    currencyRepository: CurrencyRepository,
    private val userRepository: UserRepository,
) : ViewModel() {

    val currency: Flow<Currency> = userRepository.coinsCurrency
    val rates: Flow<Rates> = currency.flatMapLatest(currencyRepository::rates)

    fun setCurrency(currency: Currency) {
        userRepository.setCoinsCurrency(currency)
    }
}