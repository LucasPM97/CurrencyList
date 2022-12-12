package com.lucas.core.utils.extensions

import com.lucas.core.data.models.ExchangePlatformType

fun ExchangePlatformType.getName(): String = when (this) {
    ExchangePlatformType.Buenbit -> "Buenbit"
    ExchangePlatformType.Binance -> "Binance"
    ExchangePlatformType.Ripio -> "Ripio"
    else -> ""
}