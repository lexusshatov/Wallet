package com.example.data

data class Convert(
    val lastUpdate: Long = 0,
    val base: Currency = Currency.Nothing,
    val to: Currency = Currency.Nothing,
    val amount: Double = 0.0,
    val converted: Double = 0.0,
    val rate: Double = 0.0,
)
