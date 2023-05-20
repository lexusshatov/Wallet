package com.mouse.wallet.ui.screen.coins

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.Currency
import com.example.data.Rates
import com.mouse.wallet.R
import com.mouse.wallet.data.CurrencyUI
import com.mouse.wallet.data.toUi
import com.mouse.wallet.ui.theme.DarkWight
import com.mouse.wallet.ui.theme.Green
import com.mouse.wallet.ui.theme.LightGraySmooth
import com.mouse.wallet.util.formatted
import com.mouse.wallet.viewmodel.CurrencyViewModel
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random

typealias Rate = Pair<Currency, Double>

@Composable
fun CurrencyScreen(
    currencyViewModel: CurrencyViewModel = koinViewModel(),
) {
    val base: Currency by currencyViewModel.currency.collectAsState(initial = Currency.USD)
    val rates: Rates by currencyViewModel.rates.collectAsState(initial = Rates())

    Column(
        modifier = Modifier.padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CurrencyCard(currency = base.toUi())
        BalanceBlock(rates = rates)
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            Text(modifier = Modifier.weight(1f), text = stringResource(R.string.name))
            Text(modifier = Modifier.weight(1f), text = stringResource(R.string.price))
            Text(modifier = Modifier.weight(1f), text = stringResource(R.string._24h_change))
        }
        Card(
            modifier = Modifier.padding(top = 8.dp),
            colors = CardDefaults.cardColors(containerColor = DarkWight),
            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
        ) {
            LazyColumn {
                rates.rates.forEach { (currency, rate) ->
                    item {
                        CurrencyItem(rate = currency to rate) {
                            currencyViewModel.setCurrency(currency)
                        }
                        Divider(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CurrencyCard(currency: CurrencyUI) {
    Card(
        modifier = Modifier.padding(all = 10.dp),
        colors = CardDefaults.cardColors(containerColor = DarkWight)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.padding(all = 4.dp),
                colors = CardDefaults.cardColors(LightGraySmooth)
            ) {
                Icon(
                    modifier = Modifier.padding(10.dp),
                    painter = painterResource(id = currency.iconRes),
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
                    text = stringResource(id = currency.titleRes),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun BalanceBlock(rates: Rates) {
    val currencyValue = (0..1000).random() + Random.nextDouble(0.0, 1.0)
    Text(
        modifier = Modifier.padding(top = 4.dp),
        text = stringResource(R.string.my_balance),
        style = MaterialTheme.typography.labelLarge,
        color = Color.Gray
    )
    Text(
        text = currencyValue.formatted(),
        style = MaterialTheme.typography.displaySmall
    )
    val toUsd = rates.rates.entries
        .firstOrNull() { it.key == Currency.USD }
        .let { usd -> (usd?.value ?: 1.0) * currencyValue }
    Text(
        text = stringResource(R.string.approximately_to_usd, toUsd),
        style = MaterialTheme.typography.labelLarge,
        color = Color.Gray
    )
}

@Composable
fun CurrencyItem(
    modifier: Modifier = Modifier,
    rate: Rate,
    onClick: () -> Unit,
) {
    val currency = rate.first
    val price = 1.0 / rate.second

    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
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
            text = price.formatted(),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier.weight(1f),
            text = "0.0%",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}