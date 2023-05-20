package com.example.domain.repository.currency.local.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.data.Currency
import com.example.data.base.JSON
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

@Entity
data class RatesEntity(
    @ColumnInfo(defaultValue = "0") val lastUpdate: Long,
    @ColumnInfo(defaultValue = "") @PrimaryKey val base: Currency,
    @ColumnInfo(defaultValue = "{}") val rates: Map<Currency, Double>,
)

class RatesConverter {
    @TypeConverter
    fun toRates(rates: String): Map<Currency, Double> = JSON.decodeFromString(rates)

    @TypeConverter
    fun fromRates(rates: Map<Currency, Double>): String = JSON.encodeToString(rates)
}