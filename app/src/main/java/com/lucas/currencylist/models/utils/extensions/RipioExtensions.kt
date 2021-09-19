package com.lucas.currencylist.models.utils.extensions

import com.lucas.currencylist.models.CurrencyType
import com.lucas.currencylist.models.CurrencyValue
import com.lucas.currencylist.models.ripio.RipioCurrency

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
    exchangeValue = bid
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