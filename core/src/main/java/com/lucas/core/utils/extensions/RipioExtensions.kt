package com.lucas.core.utils.extensions

import com.lucas.core.models.CurrencyType
import com.lucas.core.models.CurrencyValue
import com.lucas.core.models.TradingPlatformType
import com.lucas.core.models.ripio.RipioCurrency

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
    platform = TradingPlatformType.Ripio
)

fun RipioCurrency.getCurrencyType(currencyName: String): CurrencyType =
    when (currencyName.lowercase()) {
        "btc" -> CurrencyType.BTC
        "eth" -> CurrencyType.ETH
        "usdc" -> CurrencyType.USDC
        "dai" -> CurrencyType.DAI
        "ars" -> CurrencyType.ARS
        else -> CurrencyType.NONE
    }