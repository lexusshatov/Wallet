package com.mouse.wallet.ui.screen.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.data.Rates
import com.mouse.wallet.ui.ScreenState
import com.mouse.wallet.viewmodel.CurrencyViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CurrencyScreen(
    screenState: ScreenState,
    currencyViewModel: CurrencyViewModel = koinViewModel(),
) {
    val rates: Rates by currencyViewModel.rates.collectAsState(initial = Rates())
    Text(text = rates.toString())
}