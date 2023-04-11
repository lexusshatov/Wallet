package com.example.data

import kotlinx.serialization.Serializable

@Serializable
data class Rates(
    val lastUpdate: Long = 0,
    val base: Currency = Currency.Nothing,
    val rates: Map<Currency, Double> = emptyMap(),
)
