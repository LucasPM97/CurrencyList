package com.lucas.core.domain.extensions

import com.lucas.core.data.models.ExchangePlatformType
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.data.models.TradingPlatformUpdates
import com.lucas.core.domain.useCases.PlatformState

fun ExchangePlatformType.getName(): String = when (this) {
    ExchangePlatformType.Buenbit -> "Buenbit"
    ExchangePlatformType.Binance -> "Binance"
    ExchangePlatformType.Ripio -> "Ripio"
    else -> ""
}

fun TradingPlatformUpdates.toPlatformState(exchangeValues: List<ExchangeValue>) =
    PlatformState(
        platformType = platformType,
        lastUpdate = lastUpdate,
        exchangeValues = exchangeValues.filterByPlatform(platformType)
    )