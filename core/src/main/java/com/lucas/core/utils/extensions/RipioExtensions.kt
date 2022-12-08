package com.lucas.core.utils.extensions

import com.lucas.core.data.models.CurrencyType
import com.lucas.core.data.models.CurrencyValue
import com.lucas.core.data.models.TradingPlatformType
import com.lucas.core.data.models.ripio.RipioCurrency

val usedCurrencies = listOf(
    CurrencyType.BTC to CurrencyType.USDC,
    CurrencyType.ETH to CurrencyType.USDC,
    CurrencyType.USDC to CurrencyType.ARS,
    CurrencyType.DAI to CurrencyType.ARS,
    CurrencyType.ETH to CurrencyType.BTC
)

fun List<RipioCurrency>.filterNoUsedCurrencies(): List<RipioCurrency> = filter {
    usedCurrencies.contains(
        it.getCurrencyType(it.base) to it.getCurrencyType(it.quote)
    )
}

fun List<RipioCurrency>.toCurrencyList() = map {
    it.toCurrencyValue()
}

fun RipioCurrency.toCurrencyValue(): CurrencyValue = CurrencyValue(
    exchangeFrom = this.getCurrencyType(quote),
    exchangeTo = this.getCurrencyType(base),
    exchangeValue = price,
    platform = com.lucas.core.data.models.TradingPlatformType.Ripio
)

fun RipioCurrency.getCurrencyType(currencyName: String): CurrencyType =
    when (currencyName.lowercase()) {
        "btc" -> com.lucas.core.data.models.CurrencyType.BTC
        "eth" -> com.lucas.core.data.models.CurrencyType.ETH
        "usdc" -> com.lucas.core.data.models.CurrencyType.USDC
        "dai" -> com.lucas.core.data.models.CurrencyType.DAI
        "ars" -> com.lucas.core.data.models.CurrencyType.ARS
        else -> com.lucas.core.data.models.CurrencyType.NONE
    }