package com.lucas.core.domain.extensions

import com.lucas.core.data.models.buenbit.BuenbitObject
import com.lucas.core.data.models.CurrencyType
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.data.models.buenbit.BuenbitCurrency


fun BuenbitObject.toCurrencyList(): List<ExchangeValue> = listOf(
    daiars.toExchangeValue(),
    daiusd.toExchangeValue(),
    btcdai.toExchangeValue(),
    ethdai.toExchangeValue(),
    bnbdai.toExchangeValue()
)

fun BuenbitCurrency.toExchangeValue(): ExchangeValue = ExchangeValue(
    exchangeFrom = this.getCurrencyType(currency),
    exchangeTo = this.getCurrencyType(bidCurrency),
    exchangeValue = sellingPrice,
    platform = com.lucas.core.data.models.ExchangePlatformType.Buenbit
)

fun BuenbitCurrency.getCurrencyType(currencyName: String): CurrencyType = when (currencyName) {
    "ars" -> CurrencyType.ARS
    "usd" -> CurrencyType.USD
    "dai" -> CurrencyType.DAI
    "btc" -> CurrencyType.BTC
    "eth" -> CurrencyType.ETH
    "bnb" -> CurrencyType.BNB
    else -> CurrencyType.NONE
}