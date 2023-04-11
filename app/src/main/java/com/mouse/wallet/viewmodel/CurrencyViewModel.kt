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
    private val currencyRepository: CurrencyRepository,
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val rates = currencyRepository.rates(Currency.USD)
            _rates.value = rates
        }
    }

    private val _rates = MutableStateFlow(Rates())
    val rates: Flow<Rates> = _rates
}