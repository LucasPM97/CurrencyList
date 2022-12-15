package com.lucas.core.domain.extensions

import com.lucas.core.data.models.CurrencyType
import com.lucas.core.data.models.ExchangePlatformType
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.data.models.binance.BinanceExchangeValue


fun List<BinanceExchangeValue>.filterNoUsedCurrencies(): List<BinanceExchangeValue> = filter {
    it.getCurrencyType() != CurrencyType.NONE
}

fun List<BinanceExchangeValue>.toExchangeValueList() = map {
    it.toExchangeValue()
}

fun BinanceExchangeValue.toExchangeValue(): ExchangeValue = ExchangeValue(
    exchangeFrom = CurrencyType.USD,
    exchangeTo = this.getCurrencyType(),
    exchangeValue = price,
    platform = ExchangePlatformType.Binance
)

fun BinanceExchangeValue.getCurrencyType(): CurrencyType =
    when (name.lowercase()) {
        "btc" -> CurrencyType.BTC
        "eth" -> CurrencyType.ETH
        "bnb" -> CurrencyType.BNB
        else -> CurrencyType.NONE
    }