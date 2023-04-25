package com.example.domain.repository.currency.local.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.data.Currency
import com.example.data.base.JSON
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

@Entity
data class RatesEntity(
    val lastUpdate: Long = 0,
    @PrimaryKey val base: Currency = Currency.Nothing,
    val rates: Map<Currency, Double> = emptyMap(),
)

class RatesConverter {
    @TypeConverter
    fun toRates(rates: String): Map<Currency, Double> = JSON.decodeFromString(rates)

    @TypeConverter
    fun fromRates(rates: Map<Currency, Double>): String = JSON.encodeToString(rates)
}