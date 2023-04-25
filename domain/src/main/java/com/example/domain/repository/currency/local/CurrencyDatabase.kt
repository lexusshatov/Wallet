package com.example.domain.repository.currency.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.domain.repository.currency.local.data.RatesConverter
import com.example.domain.repository.currency.local.data.RatesEntity

@Database(entities = [RatesEntity::class], version = 1)
@TypeConverters(RatesConverter::class)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun getDao(): CurrencyDao
}