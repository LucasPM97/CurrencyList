package com.lucas.core.domain.extensions

import com.lucas.core.data.models.CurrencyType
import com.lucas.core.data.models.ExchangePlatformType
import com.lucas.core.data.models.ExchangeValue


fun CurrencyType.getName(): String = when (this) {
    CurrencyType.DAI -> "DAI"
    CurrencyType.BTC -> "BTC"
    CurrencyType.ETH -> "ETH"
    CurrencyType.ARS -> "ARS"
    CurrencyType.USD -> "USD"
    CurrencyType.USDC -> "USDC"
    CurrencyType.BNB -> "BNB"
    else -> ""
}

fun List<ExchangeValue>.filterByPlatform(platform: ExchangePlatformType) = filter {
    it.platform == platform
}

fun List<ExchangeValue>.anyPlatform(platform: ExchangePlatformType) = any {
    it.platform == platform
}