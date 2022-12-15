package com.lucas.core.domain.extensions

import com.lucas.core.data.models.CurrencyType
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.data.models.ripio.RipioExchangeValue

val usedCurrencies = listOf(
    CurrencyType.BTC to CurrencyType.USDC,
    CurrencyType.ETH to CurrencyType.USDC,
    CurrencyType.USDC to CurrencyType.ARS,
    CurrencyType.DAI to CurrencyType.ARS,
    CurrencyType.ETH to CurrencyType.BTC
)

fun List<RipioExchangeValue>.filterNoUsedCurrencies(): List<RipioExchangeValue> = filter {
    usedCurrencies.contains(
        it.getCurrencyType(it.base) to it.getCurrencyType(it.quote)
    )
}

fun List<RipioExchangeValue>.toExchangeValueList() = map {
    it.toExchangeValue()
}

fun RipioExchangeValue.toExchangeValue(): ExchangeValue = ExchangeValue(
    exchangeFrom = this.getCurrencyType(quote),
    exchangeTo = this.getCurrencyType(base),
    exchangeValue = price,
    platform = com.lucas.core.data.models.ExchangePlatformType.Ripio
)

fun RipioExchangeValue.getCurrencyType(currencyName: String): CurrencyType =
    when (currencyName.lowercase()) {
        "btc" -> CurrencyType.BTC
        "eth" -> CurrencyType.ETH
        "usdc" -> CurrencyType.USDC
        "dai" -> CurrencyType.DAI
        "ars" -> CurrencyType.ARS
        else -> CurrencyType.NONE
    }