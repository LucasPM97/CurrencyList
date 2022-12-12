package com.lucas.core.utils.extensions

import com.lucas.core.data.models.CurrencyType
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.data.models.binance.BinanceCurrency


fun List<BinanceCurrency>.filterNoUsedCurrencies(): List<BinanceCurrency> = filter {
    it.getCurrencyType() != CurrencyType.NONE
}

fun List<BinanceCurrency>.toCurrencyList() = map {
    it.toExchangeValue()
}

fun BinanceCurrency.toExchangeValue(): ExchangeValue = ExchangeValue(
    exchangeFrom = CurrencyType.USD,
    exchangeTo = this.getCurrencyType(),
    exchangeValue = price,
    platform = com.lucas.core.data.models.ExchangePlatformType.Binance
)

fun BinanceCurrency.getCurrencyType(): CurrencyType =
    when (name.lowercase()) {
        "btc" -> CurrencyType.BTC
        "eth" -> CurrencyType.ETH
        "bnb" -> CurrencyType.BNB
        else -> CurrencyType.NONE
    }