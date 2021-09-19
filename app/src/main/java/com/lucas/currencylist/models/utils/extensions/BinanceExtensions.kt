package com.lucas.currencylist.models.utils.extensions

import com.lucas.currencylist.models.CurrencyType
import com.lucas.currencylist.models.CurrencyValue
import com.lucas.currencylist.models.binance.BinanceCurrency


fun List<BinanceCurrency>.filterNoUsedCurrencies(): List<BinanceCurrency> = filter {
    it.getCurrencyType() != CurrencyType.NONE
}

fun List<BinanceCurrency>.toCurrencyList() = map {
    it.toCurrencyValue()
}

fun BinanceCurrency.toCurrencyValue(): CurrencyValue = CurrencyValue(
    exchangeFrom = CurrencyType.USD,
    exchangeTo = this.getCurrencyType(),
    exchangeValue = price
)

fun BinanceCurrency.getCurrencyType(): CurrencyType =
    when (name.lowercase()) {
        "btc" -> CurrencyType.BTC
        "eth" -> CurrencyType.ETH
        "bnb" -> CurrencyType.BNB
        else -> CurrencyType.NONE
    }