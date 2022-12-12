package com.lucas.core.domain.extensions

import com.lucas.core.data.models.CurrencyType


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