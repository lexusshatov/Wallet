package com.mouse.wallet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Currency
import com.example.data.Rates
import com.mouse.core.api.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CurrencyViewModel(
    currencyRepository: CurrencyRepository,
) : ViewModel() {

    private val _rates = currencyRepository.rates(Currency.USD)
    val rates: Flow<Rates> = _rates
}