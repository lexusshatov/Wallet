package com.mouse.wallet.data

import androidx.annotation.StringRes
import com.example.data.Currency
import com.example.data.base.JSON
import com.mouse.wallet.R
import kotlinx.serialization.SerialName
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

@kotlinx.serialization.Serializable
enum class CurrencyUI(
    @StringRes val titleRes: Int = R.string.usd_title,
) {
    USD(titleRes = R.string.usd_title),
    EUR,
    JPY,
    BGN,
    CZK,
    DKK,
    GBP,
    HUF,
    PLN,
    RON,
    SEK,
    CHF,
    ISK,
    NOK,
    TRY,
    AUD,
    BRL,
    CAD,
    CNY,
    HKD,
    IDR,
    ILS,
    INR,
    KRW,
    MXN,
    MYR,
    NZD,
    PHP,
    SGD,
    THB,
    ZAR,

    @SerialName("")
    Nothing;
}

fun Currency.toUi(): CurrencyUI {
    return JSON.decodeFromString(JSON.encodeToString(this))
}
