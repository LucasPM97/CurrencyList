package com.lucas.core.utils.extensions

import com.lucas.core.data.models.CurrencyType
import com.lucas.core.data.models.CurrencyValue
import com.lucas.core.data.models.binance.BinanceCurrency


fun List<BinanceCurrency>.filterNoUsedCurrencies(): List<BinanceCurrency> = filter {
    it.getCurrencyType() != CurrencyType.NONE
}

fun List<BinanceCurrency>.toCurrencyList() = map {
    it.toCurrencyValue()
}

fun BinanceCurrency.toCurrencyValue(): CurrencyValue = CurrencyValue(
    exchangeFrom = com.lucas.core.data.models.CurrencyType.USD,
    exchangeTo = this.getCurrencyType(),
    exchangeValue = price,
    platform = com.lucas.core.data.models.ExchangePlatformType.Binance
)

fun BinanceCurrency.getCurrencyType(): CurrencyType =
    when (name.lowercase()) {
        "btc" -> com.lucas.core.data.models.CurrencyType.BTC
        "eth" -> com.lucas.core.data.models.CurrencyType.ETH
        "bnb" -> com.lucas.core.data.models.CurrencyType.BNB
        else -> com.lucas.core.data.models.CurrencyType.NONE
    }