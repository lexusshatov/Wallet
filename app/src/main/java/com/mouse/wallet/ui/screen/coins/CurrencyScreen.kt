package com.mouse.wallet.ui.screen.coins

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.Currency
import com.example.data.Rates
import com.mouse.wallet.ui.ScreenState
import com.mouse.wallet.ui.theme.DarkWight
import com.mouse.wallet.ui.theme.Green
import com.mouse.wallet.viewmodel.CurrencyViewModel
import org.koin.androidx.compose.koinViewModel

typealias Rate = Pair<Currency, Double>

@Composable
fun CurrencyScreen(
    screenState: ScreenState,
    currencyViewModel: CurrencyViewModel = koinViewModel(),
) {
    val rates: Rates by currencyViewModel.rates.collectAsState(initial = Rates())
    Column(
        modifier = Modifier.padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CurrencyCard(currency = rates.base)
        val currencyValue = (0..1000).random()
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = currencyValue.toString(),
            style = MaterialTheme.typography.displayMedium
        )
        Card(colors = CardDefaults.cardColors(containerColor = Color.LightGray)) {
            LazyColumn {
                rates.rates.forEach {
                    val rate: Rate = it.key to it.value
                    item {
                        CurrencyItem(rate = rate) {
                            //TODO on click
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CurrencyCard(currency: Currency) {
    Card(
        modifier = Modifier.padding(all = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.padding(all = 4.dp),
                colors = CardDefaults.cardColors(DarkWight)
            ) {
                Icon(
                    modifier = Modifier.padding(10.dp),
                    painter = painterResource(id = com.mouse.wallet.R.drawable.ic_usd),
                    contentDescription = "Currency icon",
                    tint = Green
                )
            }
            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                Text(
                    text = currency.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = currency.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun CurrencyItem(
    modifier: Modifier = Modifier,
    rate: Rate,
    onClick: () -> Unit,
) {
    val currency = rate.first
    val price = rate.second

    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() }
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = currency.toString(),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier.weight(1f),
            text = price.toString(),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}