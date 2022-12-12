package com.lucas.core.utils.extensions

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
    "ars" -> com.lucas.core.data.models.CurrencyType.ARS
    "usd" -> com.lucas.core.data.models.CurrencyType.USD
    "dai" -> com.lucas.core.data.models.CurrencyType.DAI
    "btc" -> com.lucas.core.data.models.CurrencyType.BTC
    "eth" -> com.lucas.core.data.models.CurrencyType.ETH
    "bnb" -> com.lucas.core.data.models.CurrencyType.BNB
    else -> com.lucas.core.data.models.CurrencyType.NONE
}